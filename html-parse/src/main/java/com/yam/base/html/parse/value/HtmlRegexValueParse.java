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
import java.util.regex.Pattern;

import org.jsoup.nodes.Element;

import com.yam.base.html.parse.config.ParseMethodConfig;

/**
 * Function: TODO<br>
 *
 * @author	youaremoon
 * @version
 * @Date	Jul 14, 2014 2:10:43 PM
 */
public class HtmlRegexValueParse implements IHtmlValueParse {
	/* (non-Javadoc)
	 * @see com.yam.base.html.parse.IHtmlValueParse#getValue(com.yam.base.html.parse.config.ParseMethodConfig, org.jsoup.nodes.Element)
	 */
	@Override
	public String getValue(ParseMethodConfig methodConfig, Element ele) {
		Matcher matcher = getMatcher(methodConfig, ele.html());
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
		Matcher matcher = getMatcher(methodConfig, ele.html());
		
		List<String> list = new ArrayList<String>();
		while (matcher.find()) {
			list.add(matcher.group());
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.yam.base.html.parse.IHtmlValueParse#getValueGroup(com.yam.base.html.parse.config.ParseMethodConfig, org.jsoup.nodes.Element)
	 */
	@Override
	public List<String[]> getValueGroup(ParseMethodConfig methodConfig, Element ele) {
		throw new UnsupportedOperationException(methodConfig.getParseType().toString());
	}

	protected Matcher getMatcher(ParseMethodConfig methodConfig, String value) {
		String regex = methodConfig.getRegex();
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(value);
	}
}
