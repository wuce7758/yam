/**
 * HtmlExpressionValueParse.java <br>
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
package com.yam.base.html.parse.value;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.yam.base.html.jsoup.SingleValueAnalyse;
import com.yam.base.html.parse.config.ParseMethodConfig;

/**
 * Function: TODO<br>
 *
 * @author	youaremoon
 * @version
 * @Date	Jul 14, 2014 2:10:43 PM
 */
public class HtmlRegexExpValueParse extends HtmlRegexValueParse {
	/* (non-Javadoc)
	 * @see com.yam.base.html.parse.IHtmlValueParse#getValue(com.yam.base.html.parse.config.ParseMethodConfig, org.jsoup.nodes.Element)
	 */
	@Override
	public String getValue(ParseMethodConfig methodConfig, Element ele) {
		Matcher matcher = getMatcher(methodConfig, ele.html());
		String value;
		if (matcher.find()) {
			value = matcher.group();
		} else {
			return null;
		}
		
		Document doc = Jsoup.parse(value);
		return SingleValueAnalyse.getValueByExp(doc, methodConfig.getExpression());
	}

	/* (non-Javadoc)
	 * @see com.yam.base.html.parse.IHtmlValueParse#getValues(com.yam.base.html.parse.config.ParseMethodConfig, org.jsoup.nodes.Element)
	 */
	@Override
	public List<String> getValues(ParseMethodConfig methodConfig, Element ele) {
		Matcher matcher = getMatcher(methodConfig, ele.html());
		
		List<String> list = new ArrayList<String>();
		while (matcher.find()) {
			list.add(matcher.group());
		}
		
		int size = list.size();
		if (size == 0) {
			return null;
		}
		
		List<String> finalList = new ArrayList<String>(size);
		for (int i = 0; i < size; i++) {
			Document doc = Jsoup.parse(list.get(i));
			String value = SingleValueAnalyse.getValueByExp(doc, methodConfig.getExpression());
			if (null != value) {
				finalList.add(value);
			}
		}
		
		if (finalList.isEmpty()) {
			return null;
		}
		
		return finalList;
	}

}
