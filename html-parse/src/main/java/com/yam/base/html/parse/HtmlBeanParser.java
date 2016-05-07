/**
 * HtmlBeanParser.java <br>
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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.yam.base.html.jsoup.JSoupUtil;
import com.yam.base.html.parse.config.ParseBeanConfig;
import com.yam.base.html.parse.result.IParseResult;
import com.yam.base.html.parse.result.ParseResultFactory;

/**
 * Function: TODO<br>
 *
 * @author	youaremoon
 * @version
 * @Date	Jul 14, 2014 12:27:08 AM
 */
public class HtmlBeanParser {
	private ParseBeanConfig beanConfig;
	
	public HtmlBeanParser(ParseBeanConfig beanConfig) {
		this.beanConfig = beanConfig;
	}
	
	public Object parseUrl(String url) throws Exception {
		return parse(JSoupUtil.parse(url));
	}
	
	public Object parseUrl(String url, int tryTime) throws Exception {
		return parse(JSoupUtil.parseWithRetry(url, tryTime));
	}
	
	public Object parseHtml(String html) throws Exception {
		return parse(Jsoup.parse(html));
	}
	
	public Object parse(Document doc) throws Exception {
//		System.out.println(doc);
//		
		String className = beanConfig.getClassConfig().getClassName();
		IParseResult pr = ParseResultFactory.get(className);
		if (null == pr) {
			throw new UnsupportedOperationException("can not find parser to handle this class:" + className);
		}
		
		return pr.getResult(doc, beanConfig);
	}
	
	
}
