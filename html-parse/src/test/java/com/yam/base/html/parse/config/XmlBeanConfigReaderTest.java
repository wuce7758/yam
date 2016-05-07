/**
 * XmlBeanConfigReaderTest.java <br>
 * com.yam.base.html.parse.config <br>
 *
 * Function： TODO <br>
 *
 *   ver     date      		author		<br>
 * ──────────────────────────────────	<br>
 *   1.0	 Jul 14, 2014		youaremoon	<br>
 *
 * Copyright (c) 2013 YangBo, All Rights Reserved.<br>
 */
package com.yam.base.html.parse.config;

import java.io.InputStream;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * Function: TODO<br>
 * 
 * @author youaremoon
 * @version
 * @Date Jul 14, 2014 9:06:31 PM
 */
public class XmlBeanConfigReaderTest {
	@Test
	public void testReadFromInputStream() {
		InputStream inputStream = XmlBeanConfigReaderTest.class
				.getResourceAsStream("/com/yam/base/html/parse/apps.xml");
		XmlBeanConfigReader configReader = new XmlBeanConfigReader(inputStream);
		
		Map<String, ParseBeanConfig> beanConfigMap = configReader.getConfigMap();
		TestCase.assertNotNull(beanConfigMap);
		
		ParseBeanConfig beanConfig = beanConfigMap.get("baidu-app-info");
		TestCase.assertNotNull(beanConfig);
		
		Map<String, ParseMethodConfig> methodConfigMap = beanConfig.getMethodMap();
		TestCase.assertNotNull(methodConfigMap);
		
		ParseMethodConfig methodConfig = methodConfigMap.get("categoryName");
		TestCase.assertNotNull(methodConfig);
		
		System.out.println(methodConfig);
	}
}
