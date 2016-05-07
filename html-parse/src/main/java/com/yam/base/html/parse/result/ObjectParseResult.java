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

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.yam.base.bean.BeanUtil;
import com.yam.base.bean.MethodsUtil;
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
public class ObjectParseResult implements IParseResult {
	public Object getResult(Document doc, ParseBeanConfig beanConfig) throws Exception {
		String className = beanConfig.getClassConfig().getClassName();
		
		Class<?> tClass = Class.forName(className);
		Object t = tClass.newInstance();
		Method[] ms = MethodsUtil.getMethods(tClass);
		
		boolean hasSet = false;
		Map<String, ParseMethodConfig> methodMap = beanConfig.getMethodMap();
		for (ParseMethodConfig methodConfig : methodMap.values()) {
			try {
				if (fillValueToObject(doc, methodConfig, t, ms)) {
					hasSet = true;
				}
			} catch (Exception e) {
				// ignore
				if (!methodConfig.isIgnoreError()) {
					throw new Exception(
							"parse method error, class name:" + className + ", method name:" + methodConfig.getMethodName() , e);
				}
			}
		}
		
		return hasSet ? t : null;
	}
	
	private boolean fillValueToObject(Element ele, ParseMethodConfig methodConfig, Object obj, Method[] ms) throws Exception {
		String methodName = methodConfig.getMethodName();
		
		String setMethodName = BeanUtil.DEFAULT_NAME_TRANSFER.getGetMethod(methodName);
		Method setMethod = MethodsUtil.getMethod(obj.getClass(), setMethodName);
		
		IHtmlValueParse valueParse = HtmlValueParseFactory.getParser(methodConfig.getParseType());
		
		Class<?> returnType = setMethod.getReturnType();
		if (returnType.isArray() || returnType.isAssignableFrom(List.class)) {
			//list， 需要将其转换为对应的类型
			List<String> value = valueParse.getValues(methodConfig, ele);
			
			if (null != value && !value.isEmpty()) {
				return BeanUtil.setObjectValueToObject(obj, ms, methodName, value);
			}
		} else if (returnType.isAssignableFrom(Map.class)) {
			//map
			List<String[]> value = valueParse.getValueGroup(methodConfig, ele);
			Map<String, String> map = new HashMap<String, String>();
			for (String[] v : value) {
				map.put(v[0], v[1]);
			}
			
			if (null != map && !map.isEmpty()) {
				return BeanUtil.setObjectValueToObject(obj, ms, methodName, value);
			}
		} else {
			String value = valueParse.getValue(methodConfig, ele);
			if (null != value) {
				return BeanUtil.setStringValueToObject(obj, ms, methodName, value);
			}
		}
		
		return false;
	}
}
