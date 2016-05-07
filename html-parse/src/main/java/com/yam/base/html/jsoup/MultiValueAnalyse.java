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
 * Function: jsoup文档分析,通过表达式获取元素中的多个值<br>
 * 
 * @author youaremoon
 * @version
 * @Date Jul 12, 2014 11:42:54 AM
 */
public class MultiValueAnalyse extends JSoupDocAnalyse {
	
	/**
	 * 通过表达式获取元素中的多个值
	 * @param ele
	 * @param expression
	 * @return 
	 * List<String>
	 */
	public static List<String> getValueListByExp(Element ele, String expression) {
		HtmlElementExpressionParser parser = new HtmlElementExpressionParser(expression);
		Element lastChild = ele;
		Elements lastChildren = null;
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
				} else if (isAllowValue(key)) {
					// 最终取值的部分，如果前一个解析到的为单个元素，则结果中只返回一个数据，否则则返回多个
					// class:cls->tag:a->attr:href，表示获取class为cls的第一个元素下所有tag为a的元素的href属性
					List<String> valList = new ArrayList<String>();
					if (null == lastChildren) {
						valList.add(getValue(lastChild, single, true));
					} else {
						for (int i = 0, size = lastChildren.size(); i < size; i++) {
							String val = getValue(lastChildren.get(i), single, true);
							valList.add(val);
						}
					}
					
					return valList;
				}
			} else {
				// 最后的表达中包含一组表达式时，对每一个表达式进行解析
				// class:a->[id:packa->text, id:packb->text]
				HtmlElementMulti multi = (HtmlElementMulti)detail;
				List<HtmlElementSingle> list = multi.getDetailList();
		        int arrayLen = list.size();
		        List<String> valList = new ArrayList<String>(arrayLen);
		        for (int i = 0; i < arrayLen; i++) {
		        	List<String> vals = getValueListByExp(lastChild, list.get(i).getExpression());
		        	if (null != vals) {
		        		valList.addAll(vals);
		        	}
		        }

		        return valList;
			}
		}

		return null;
	}
}
