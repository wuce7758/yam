/**
 * ParseMethodConfig.java <br>
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
 * @Date	Jul 13, 2014 11:12:04 PM
 */
public class ParseBaseConfig {
	private String expression;
	private String regex;
	private String resultType;
	private ParseMethodType parseType;
	private boolean ignoreError;
	
	public String toString() {
		return "expression:" + expression
				+ ",regex:" + regex
				+ ",resultType:" + resultType
				+ ",parseType:" + parseType
				+ ",ignoreError:" + ignoreError;
	}
	
	/**
	 * regex
	 *
	 * @return  the regex
	 * @since   1.0.0
	 */
	public String getRegex() {
		return regex;
	}
	/**
	 * @param regex the regex to set
	 */
	public void setRegex(String regex) {
		this.regex = regex;
	}
	/**
	 * parseType
	 *
	 * @return  the parseType
	 * @since   1.0.0
	 */
	public ParseMethodType getParseType() {
		return parseType;
	}
	/**
	 * @param parseType the parseType to set
	 */
	public void setParseType(ParseMethodType parseType) {
		this.parseType = parseType;
	}
	/**
	 * expression
	 *
	 * @return  the expression
	 * @since   1.0.0
	 */
	public String getExpression() {
		return expression;
	}
	/**
	 * @param expression the expression to set
	 */
	public void setExpression(String expression) {
		this.expression = expression;
	}
	/**
	 * ignoreError
	 *
	 * @return  the ignoreError
	 * @since   1.0.0
	 */
	public boolean isIgnoreError() {
		return ignoreError;
	}
	/**
	 * @param ignoreError the ignoreError to set
	 */
	public void setIgnoreError(boolean ignoreError) {
		this.ignoreError = ignoreError;
	}
	/**
	 * resultType
	 *
	 * @return  the resultType
	 * @since   1.0.0
	 */
	public String getResultType() {
		return resultType;
	}
	/**
	 * @param resultType the resultType to set
	 */
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}
	
}
