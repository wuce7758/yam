/**
 * ParseBeanConfig.java <br>
 * com.yam.base.html.parse.config <br>
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

import java.util.HashMap;
import java.util.Map;

/**
 * Function: 被解析的实体配置<br>
 *
 * @author	youaremoon
 * @version
 * @Date	Jul 13, 2014 10:56:48 PM
 */
public class ParseBeanConfig {
	private String tag;
	private ParseClassConfig classConfig;
	private Map<String, ParseMethodConfig> methodMap;
	/**
	 * tag
	 *
	 * @return  the tag
	 * @since   1.0.0
	 */
	public String getTag() {
		return tag;
	}
	/**
	 * @param tag the tag to set
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}
	/**
	 * className
	 *
	 * @return  the className
	 * @since   1.0.0
	 */
	public ParseClassConfig getClassConfig() {
		return classConfig;
	}
	/**
	 * @param className the className to set
	 */
	public void setClassConfig(ParseClassConfig classConfig) {
		this.classConfig = classConfig;
	}
	/**
	 * methodMap
	 *
	 * @return  the methodMap
	 * @since   1.0.0
	 */
	public Map<String, ParseMethodConfig> getMethodMap() {
		return methodMap;
	}
	/**
	 * @param methodMap the methodMap to set
	 */
	public void setMethodMap(Map<String, ParseMethodConfig> methodMap) {
		this.methodMap = methodMap;
	}
	
	public void removeMethod(String methodName) {
		if (null == this.methodMap) {
			return;
		}
		
		this.methodMap.remove(methodName);
	}
	
	public void addMethod(String methodName, ParseMethodConfig config) {
		if (null == methodMap) {
			methodMap = new HashMap<String, ParseMethodConfig>();
		}
		
		methodMap.put(methodName, config);
	}
}
