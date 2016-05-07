/**
 * MultiValueAnalyseTest.java <br>
 * com.yam.base.html.jsoup <br>
 *
 * Function： TODO <br>
 *
 *   ver     date      		author		<br>
 * ──────────────────────────────────	<br>
 *   1.0	 Jul 13, 2014		youaremoon	<br>
 *
 * Copyright (c) 2013 YangBo, All Rights Reserved.<br>
 */
package com.yam.base.html.jsoup;

import java.io.IOException;

import junit.framework.TestCase;

import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

import com.yam.base.html.jsoup.JSoupUtil;
import com.yam.base.html.jsoup.SingleValueAnalyse;

/**
 * Function: TODO<br>
 *
 * @author	youaremoon
 * @version
 * @Date	Jul 13, 2014 12:59:37 PM
 */
public class SingleValueAnalyseTest {

	private Document doc;
	
	@Before
	public void before() throws IOException  {
		doc = JSoupUtil.parse("http://www.baidu.com");
//		System.out.println(doc);
	}
	
	@Test
	public void testGetNv0() {
		String val = SingleValueAnalyse.getValueByExp(doc, 
				"id:s_tab->tag:a->attr:href");
		
		TestCase.assertTrue(null != val);
		
		System.out.println(val);
	}
}
