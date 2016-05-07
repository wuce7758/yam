/**
 * ObjectParseResult.java <br>
 * com.yam.base.html.parse.result <br>
 *
 * Function： TODO <br>
 *
 *   ver     date      		author		<br>
 * ──────────────────────────────────	<br>
 *   1.0	 Jul 14, 2014		youaremoon	<br>
 *
 * Copyright (c) 2013 YangBo, All Rights Reserved.<br>
 */
package com.yam.base.html.parse.result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.yam.base.html.parse.config.ParseBeanConfig;
import com.yam.base.html.parse.config.ParseMethodConfig;
import com.yam.base.html.parse.value.HtmlValueParseFactory;
import com.yam.base.html.parse.value.IHtmlValueParse;

/**
 * Function: TODO<br>
 *
 * @author	youaremoon
 * @version
 * @Date	Jul 14, 2014 6:23:24 PM
 */
public class MapParseResult implements IParseResult {
	public Map<String, Object> getResult(Document doc, ParseBeanConfig beanConfig) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		boolean hasSet = false;
		Map<String, ParseMethodConfig> methodMap = beanConfig.getMethodMap();
		for (ParseMethodConfig methodConfig : methodMap.values()) {
			if (fillValueToMap(doc, methodConfig, resultMap)) {
				hasSet = true;
			}
		}
		
		return hasSet ? resultMap : null;
	}
	
	private boolean fillValueToMap(Element ele, ParseMethodConfig methodConfig, Map<String, Object> resultMap) throws Exception {
		String methodName = methodConfig.getMethodName();
		
		IHtmlValueParse valueParse = HtmlValueParseFactory.getParser(methodConfig.getParseType());
		
		String resultType = methodConfig.getResultType();
		if (null == resultType || "string".equals(resultType)) {
			String value = valueParse.getValue(methodConfig, ele);
			if (null != value) {
				resultMap.put(methodName, value);
				return true;
			}
		} else if ("list".equals(resultType)) {
			//list， 需要将其转换为对应的类型
			List<String> value = valueParse.getValues(methodConfig, ele);
			if (null != value && !value.isEmpty()) {
				resultMap.put(methodName, value);
				return true;
			}
		} else if ("map".equals(resultType)) {
			//map
			List<String[]> value = valueParse.getValueGroup(methodConfig, ele);
			Map<String, String> map = new HashMap<String, String>();
			for (String[] v : value) {
				map.put(v[0], v[1]);
			}
			
			if (null != map && !map.isEmpty()) {
				resultMap.put(methodName, map);
				return true;
			}
		} 
		
		return false;
	}
}
