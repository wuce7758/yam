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

import org.jsoup.nodes.Element;

import com.yam.base.html.jsoup.MultiValueAnalyse;
import com.yam.base.html.jsoup.SingleValueAnalyse;
import com.yam.base.html.parse.config.ParseMethodConfig;

/**
 * Function: TODO<br>
 *
 * @author	youaremoon
 * @version
 * @Date	Jul 14, 2014 2:10:43 PM
 */
public class HtmlExpRegexValueParse extends HtmlRegexValueParse {

	/* (non-Javadoc)
	 * @see com.yam.base.html.parse.IHtmlValueParse#getValue(com.yam.base.html.parse.config.ParseMethodConfig, org.jsoup.nodes.Element)
	 */
	@Override
	public String getValue(ParseMethodConfig methodConfig, Element ele) {
		String value = SingleValueAnalyse.getValueByExp(ele, methodConfig.getExpression());
		if (null == value) {
			return null;
		}
		
		Matcher matcher = getMatcher(methodConfig, value);
		if (matcher.find()) {
			return matcher.group();
		}
		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.yam.base.html.parse.IHtmlValueParse#getValues(com.yam.base.html.parse.config.ParseMethodConfig, org.jsoup.nodes.Element)
	 */
	@Override
	public List<String> getValues(ParseMethodConfig methodConfig, Element ele) {
		List<String> list = MultiValueAnalyse.getValueListByExp(ele, methodConfig.getExpression());
		if (null == list || list.isEmpty()) {
			return list;
		}
		
		int size = list.size();
		List<String> finalList = new ArrayList<String>(size);
		for (int i = 0; i < size; i++) {
			Matcher matcher = getMatcher(methodConfig, list.get(i));
			if (matcher.find()) {
				finalList.add(matcher.group());
			}
		}
		return finalList;
	}
}
