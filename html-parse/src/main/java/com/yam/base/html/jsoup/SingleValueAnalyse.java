/**
 * SingleValueAnalyse.java <br>
 * com.yam.base.html.jsoup <br>
 *
 * Function： TODO <br>
 *
 *   ver     date      		author		<br>
 * ──────────────────────────────────	<br>
 *   1.0	 Jul 12, 2014		youaremoon	<br>
 *
 * Copyright (c) 2013 YangBo, All Rights Reserved.<br>
 */
package com.yam.base.html.jsoup;

import org.jsoup.nodes.Element;

import com.yam.base.html.expression.HtmlElementExpressionParser;
import com.yam.base.html.expression.HtmlElementSingle;

/**
 * Function: jsoup文档分析,通过表达式获取元素中的单个值<br>
 * 
 * @author youaremoon
 * @version
 * @Date Jul 12, 2014 11:42:54 AM
 */
public class SingleValueAnalyse extends JSoupDocAnalyse {
	/**
	 * 通过表达式获取元素中的单个值
	 * @param ele
	 * @param expression
	 * @return 
	 * String
	 */
	public static String getValueByExp(Element ele, String expression) {
		HtmlElementExpressionParser parser = new HtmlElementExpressionParser(expression);
		Element lastChild = getElementByExp(ele, parser);
		if (null == lastChild) {
			return null;
		}
		
		HtmlElementSingle detail = (HtmlElementSingle) parser.last();
		if (isAllowValue(detail.getKey())) {
			return getValue(lastChild, detail, true);
		} else {
			return null;
		}	
	}

	
}
