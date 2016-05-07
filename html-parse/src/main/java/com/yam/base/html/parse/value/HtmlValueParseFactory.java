/**
 * HtmlValueParseFactory.java <br>
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

import com.yam.base.html.parse.config.ParseMethodType;

/**
 * Function: TODO<br>
 *
 * @author	youaremoon
 * @version
 * @Date	Jul 14, 2014 2:11:53 PM
 */
public class HtmlValueParseFactory {
	public static IHtmlValueParse getParser(ParseMethodType type) {
		switch (type) {
		case EXPRESSION:
			return new HtmlExpressionValueParse();
		case REGEX:
			return new HtmlRegexValueParse();
		case EXPRESSION_REGEX:
			return new HtmlExpRegexValueParse();
		case REGEX_EXPRESSION:
			return new HtmlRegexExpValueParse();
		default:
			return new HtmlExpressionValueParse();
		}
	}
}
