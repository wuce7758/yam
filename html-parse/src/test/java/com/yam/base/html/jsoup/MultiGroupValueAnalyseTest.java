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
import java.util.List;

import junit.framework.TestCase;

import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

import com.yam.base.html.jsoup.JSoupUtil;
import com.yam.base.html.jsoup.MultiGroupValueAnalyse;

/**
 * Function: TODO<br>
 *
 * @author	youaremoon
 * @version
 * @Date	Jul 13, 2014 12:59:37 PM
 */
public class MultiGroupValueAnalyseTest {

	private Document doc;
	
	@Before
	public void before() throws IOException  {
		doc = JSoupUtil.parse("http://www.baidu.com");
//		System.out.println(doc);
	}
	
	@Test
	public void testGetNv0() {
		List<String[]> nvList = MultiGroupValueAnalyse.getGroupValueByExp(doc, 
				"id:s_tab->tag:a->[attr:href, text]");
		
		TestCase.assertTrue(null != nvList && nvList.size() > 0);
		
		System.out.println("tab");
		for (String[] nvs : nvList) {
			for (String nv : nvs) {
				System.out.print(nv + "\t");
			}
			System.out.println();
		}
	}
}
