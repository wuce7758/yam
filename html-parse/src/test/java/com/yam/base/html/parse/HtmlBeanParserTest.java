/**
 * HtmlBeanParserTest.java <br>
 * com.yam.base.html.parse <br>
 *
 * Function： TODO <br>
 *
 *   ver     date      		author		<br>
 * ──────────────────────────────────	<br>
 *   1.0	 Jul 14, 2014		youaremoon	<br>
 *
 * Copyright (c) 2013 YangBo, All Rights Reserved.<br>
 */
package com.yam.base.html.parse;

import java.io.InputStream;

import junit.framework.TestCase;

import org.junit.Test;

import com.yam.base.html.parse.config.ParseBeanConfig;
import com.yam.base.html.parse.config.XmlBeanConfigReader;
import com.yam.base.html.parse.config.XmlBeanConfigReaderTest;

/**
 * Function: TODO<br>
 *
 * @author	youaremoon
 * @version
 * @Date	Jul 14, 2014 9:38:08 PM
 */
public class HtmlBeanParserTest {
	
	@Test
	public void testParseUrlToObject() {
		testParseUrlByTag("baidu-app-info-map");
	}
	
	@Test
	public void testParseUrlToMap() {
		testParseUrlByTag("baidu-app-info");
	}
	
	private void testParseUrlByTag(String tag) {
		InputStream inputStream = XmlBeanConfigReaderTest.class
				.getResourceAsStream("/com/yam/base/html/parse/apps.xml");
		XmlBeanConfigReader configReader = new XmlBeanConfigReader(inputStream);
		
		ParseBeanConfig beanConfig = configReader.getConfig(tag);
		HtmlBeanParser beanParser = new HtmlBeanParser(beanConfig);
		try {
			Object result = beanParser.parseUrl("http://www.wandoujia.com/apps/com.tencent.mobileqq", 3);
			TestCase.assertNotNull(result);
		
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			TestCase.assertTrue(false);
		}
	}
}
