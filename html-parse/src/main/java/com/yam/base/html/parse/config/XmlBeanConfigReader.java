/**
 * BeanConfigReader.java <br>
 * com.yam.base.html.parse <br>
 *
 * Function： TODO <br>
 *
 *   ver     date      		author		<br>
 * ──────────────────────────────────	<br>
 *   1.0	 Jul 13, 2014		youaremoon	<br>
 *
 * Copyright (c) 2013 YangBo, All Rights Reserved.<br>
 */
package com.yam.base.html.parse.config;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.yam.base.config.ConfigReader;
import com.yam.base.string.StringUtil;

/**
 * Function: xml实体配置<br>
 *
 * @author	youaremoon
 * @version
 * @Date	Jul 13, 2014 10:25:44 PM
 */
public class XmlBeanConfigReader extends ConfigReader<ParseBeanConfig> {
	
	private InputStream inputStream;
	
    public XmlBeanConfigReader(File file, int reloadMode) {
    	super(file);

    	this.setReloadMode(reloadMode);
    }
    
    public XmlBeanConfigReader(InputStream inputStream) {
    	super();
    	
    	this.inputStream = inputStream;
    	this.setReloadMode(ConfigReader.ONETIME_RELOAD);
    }

	/* (non-Javadoc)
	 * @see com.yam.base.config.ConfigReader#readConfig()
	 */
	@Override
	protected Map<String, ParseBeanConfig> readConfig() {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try{
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = null;
			if (null != this.getFile()) {
				doc = db.parse(this.getFile());
			} else if (null != this.inputStream) {
				doc = db.parse(inputStream);
			}
			
			if(null != doc) {
				Map<String, ParseBeanConfig> configMap = new HashMap<String, ParseBeanConfig>();
				parseRoot(doc.getDocumentElement(), configMap);
				
				return configMap;
			}
		} catch(Exception ex) {
			reset();
			
			ex.printStackTrace();
		}

		return null;
	}    
    
	private void parseRoot(Element rootNode, Map<String, ParseBeanConfig> configMap) {
		NodeList nodeList = rootNode.getChildNodes();
		for (int i = 0, size = nodeList.getLength(); i < size; i++) {
			Node node = nodeList.item(i);
			// 非bean-info节点暂时不解析
			if ("bean-info".equalsIgnoreCase(node.getNodeName())) {
				ParseBeanConfig config = parseBeanInfo(node);
				if (null != config) {
					configMap.put(config.getTag(), config);
				}
			}
		}
	}
	
	private ParseBeanConfig parseBeanInfo(Node parent) {
		ParseBeanConfig config = new ParseBeanConfig();
		
		NodeList nodeList = parent.getChildNodes();
		for (int i = 0, size = nodeList.getLength(); i < size; i++) {
			Node node = nodeList.item(i);
			if (!(node instanceof Element)) {
				continue;
			}
			
			Element ele = (Element)node;
			String nodeName = ele.getNodeName();
			if ("tag".equalsIgnoreCase(nodeName)) {
				config.setTag(getAttr(ele, "name"));
			} else if ("class".equals(nodeName)) {
				ParseClassConfig classConfig = new ParseClassConfig();
				classConfig.setClassName(getAttr(ele, "name"));
				parse(classConfig, ele);
				
				config.setClassConfig(classConfig);
			} else if ("method".equals(nodeName)) {
				ParseMethodConfig methodConfig = new ParseMethodConfig();
				methodConfig.setMethodName(getAttr(ele, "name"));
				parse(methodConfig, ele);
				
				config.addMethod(methodConfig.getMethodName(), methodConfig);
			}
		}
		
		// 如果tag不存在，则使用class name作为tag
		ParseClassConfig classInfo = config.getClassConfig();
		if (null == config.getTag() && null != classInfo) {
			config.setTag(classInfo.getClassName());
		}
		
		if (null == classInfo || null == classInfo.getClassName()) {
			return null;
		}
		
		return config;
	}
	
	private void parse(ParseBaseConfig baseConfig, Element ele) {
		baseConfig.setExpression(getAttr(ele, "expression"));
		baseConfig.setRegex(getAttr(ele, "regex"));
		baseConfig.setIgnoreError(Boolean.parseBoolean(getAttr(ele, "ignore-error")));
		baseConfig.setResultType(getAttr(ele, "result-type"));
		
		String parseType = getAttr(ele, "parse-type");
		if (StringUtil.isNullOrEmpty(parseType)) {
			if (null == baseConfig.getRegex()) {
				baseConfig.setParseType(ParseMethodType.EXPRESSION);
			} else if (null == baseConfig.getExpression()) {
				baseConfig.setParseType(ParseMethodType.REGEX);
			} else if (null != baseConfig.getExpression() && null != baseConfig.getRegex()) {
				baseConfig.setParseType(ParseMethodType.EXPRESSION_REGEX);
			}
		} else {
			baseConfig.setParseType(ParseMethodType.getType(parseType));
		}
	}
	
	private String getAttr(Element ele, String name) {
		String value = ele.getAttribute(name);
		if (StringUtil.isNullOrEmpty(value)) {
			return null;
		} else {
			return value;
		}
	}
}
