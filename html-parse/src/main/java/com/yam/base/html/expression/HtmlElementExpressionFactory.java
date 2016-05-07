/**
 * HtmlElementExpressionFactory.java <br>
 * com.yam.base.html.expression <br>
 *
 * Function： TODO <br>
 *
 *   ver     date      		author		<br>
 * ──────────────────────────────────	<br>
 *   1.0	 Jul 12, 2014		youaremoon	<br>
 *
 * Copyright (c) 2013 YangBo, All Rights Reserved.<br>
 */
package com.yam.base.html.expression;

/**
 * Function: 生成表达式的工厂<br>
 *
 * @author	youaremoon
 * @version
 * @Date	Jul 12, 2014 10:49:13 AM
 */
public class HtmlElementExpressionFactory {
	public static IHtmlElementExpression getExpression(String expression, boolean isSingle) {
		if (isSingle) {
			return new HtmlElementSingle(expression);
		} else {
			return new HtmlElementMulti(expression);
		}
	}
}
