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

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.yam.base.html.expression.HtmlElementExpressionParser;
import com.yam.base.html.expression.HtmlElementMulti;
import com.yam.base.html.expression.HtmlElementSingle;
import com.yam.base.html.expression.IHtmlElementExpression;

/**
 * Function: jsoup文档分析,通过表达式获取元素中的多个值对<br>
 * 
 * @author youaremoon
 * @version
 * @Date Jul 12, 2014 11:42:54 AM
 */
public class MultiGroupValueAnalyse extends JSoupDocAnalyse {
	/**
	 * 通过表达式获取元素中的多个值对
	 * @param ele
	 * @param expression
	 * @return 
	 * List<String[]>
	 */
	public static List<String[]> getGroupValueByExp(Element ele, String expression) {
		HtmlElementExpressionParser parser = new HtmlElementExpressionParser(expression);
		Element lastChild = ele;
		Elements lastChildren = null;
		List<String[]> resultList = new ArrayList<String[]>();
		while (parser.hasNext()) {
			IHtmlElementExpression detail = parser.next();
			
			if (detail.isSingle()) {
				HtmlElementSingle single = (HtmlElementSingle)detail;
				String key = single.getKey();
				String value = single.getValue();
				if (KEY_ID.equals(key)) {
					lastChild = lastChild.getElementById(value);
				} else if (isAllowKey(key)) {
					lastChildren = searchElements(lastChild, single, true);
					lastChild = searchElementByIndex(lastChildren, single, true);
				}
			} else {
				// 最后的表达中包含一组表达式时，对每一个表达式进行解析
				// class:cls->tag:a->[attr:href, text]，表示获取class为cls的第一个元素下的所有tag为a的元素，分别获取href和text字段
				HtmlElementMulti multi = (HtmlElementMulti)detail;
				List<HtmlElementSingle> list = multi.getDetailList();
		        int arrayLen = list.size();
		        for (int i = 0, size = lastChildren.size(); i < size; i++) {
		        	lastChild = lastChildren.get(i);
			        String[] vals = new String[arrayLen];
			        for (int j = 0; j < arrayLen; j++) {
			        	vals[j] = SingleValueAnalyse.getValueByExp(lastChild, list.get(j).getExpression());
			        }
			        resultList.add(vals);
		        }
		       
		        return resultList;
			}
		}

		return null;
	}
}
