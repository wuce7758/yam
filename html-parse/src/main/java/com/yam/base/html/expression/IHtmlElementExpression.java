/**
 * IHtmlElementExpression.java <br>
 * com.yam.base.html.jsoup <br>
 *
 * Function： html元素描述 <br>
 *
 *   ver     date      		author		<br>
 * ──────────────────────────────────	<br>
 *   1.0	 Jul 12, 2014		youaremoon	<br>
 *
 * Copyright (c) 2013 YangBo, All Rights Reserved.<br>
 */
package com.yam.base.html.expression;

/**
 * Function:  html元素表达式<br>
 *
 * @author	youaremoon
 * @version
 * @Date	Jul 12, 2014 10:22:39 AM
 */
public interface IHtmlElementExpression {
	/**
	 * 元素表达式分隔符
	 */
	public static final String SPLIT_ELEMENT_STRING = "->";
	
	/**
	 * 组元素开始符号-string
	 */
	public static final String SPLIT_ARRAY_START = "[";
	/**
	 * 组元素结束符号-string
	 */
	public static final String SPLIT_ARRAY_END = "]";
	/**
	 * 组元素开始符号-char
	 */
	public static final char SPLIT_ARRAY_START_CHAR = '[';
	/**
	 * 组元素结束符号-char
	 */
	public static final char SPLIT_ARRAY_END_CHAR = ']';
	
	/**
	 * 多个表达式的分隔符
	 */
	public static final char SPLIT_MULTI_EXP = ',';
	
	/**
	 * 获取完整的表达式
	 * @return String
	 */
	String getExpression();
	
	/**
	 * 是否是对单个元素的描述
	 * @return boolean
	 */
	boolean isSingle();
}
