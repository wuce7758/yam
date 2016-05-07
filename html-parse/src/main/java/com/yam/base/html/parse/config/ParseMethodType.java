/**
 * ParseMethodType.java <br>
 * com.yam.base.html.parse.config <br>
 *
 * Function： TODO <br>
 *
 *   ver     date      		author		<br>
 * ──────────────────────────────────	<br>
 *   1.0	 Jul 13, 2014		youaremoon	<br>
 *
 * Copyright (c) 2013 YangBo, All Rights Reserved.<br>
 */
package com.yam.base.html.parse.config;

/**
 * Function: TODO<br>
 *
 * @author	youaremoon
 * @version
 * @Date	Jul 13, 2014 11:48:09 PM
 */
public enum ParseMethodType {
	EXPRESSION,
	REGEX,
	EXPRESSION_REGEX,
	REGEX_EXPRESSION;

	public static ParseMethodType getType(String v) {
		if ("exp".equals(v)) {
			return EXPRESSION_REGEX;
		} else if ("regex".equals(v)) {
			return REGEX;
		} else if ("exp-regex".equals(v)) {
			return EXPRESSION_REGEX;
		} else if ("regex-exp".equals(v)) {
			return REGEX_EXPRESSION;
		} 
		
		throw new java.lang.IllegalArgumentException("unknown type " + v);
	}
}
