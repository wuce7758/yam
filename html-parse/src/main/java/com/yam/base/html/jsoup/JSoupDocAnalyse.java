/**
 * JSoupDocAnalyse.java <br>
 * com.yam.base.html.jsoup <br>
 *
 * Function： TODO <br>
 *
 *   ver     date      		author		<br>
 * ──────────────────────────────────	<br>
 *   1.0	 Jul 13, 2014		youaremoon	<br>
 *
 * Copyright (c) 2013 YangBo, All Rights Reserved.<br>
 */
package com.yam.base.html.jsoup;

import java.util.Arrays;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.yam.base.html.expression.HtmlElementExpressionParser;
import com.yam.base.html.expression.HtmlElementSingle;

/**
 * jsoup文档分析<br>
 * 1.用于提取html元素的单个值 <br>
 * 2.用于提取html元素中的一组值<br>
 * 3.用于提取html元素中的一组数组值<br>
 * 表达式的形式为:key0_index0:value0->key1:index1:value1->[key2:value2, key3:value3]<br>
 * 其中每一个子表达式为key(必须)+ index(非必须,默认为0)+value(非必须)组成，
 * 目前支持的key有class,tag,id,attribute,attr,text,html<br>
 * class:表示根据class名查找元素, 如class_1:icon_v,表示查找class为icon_v的第二个元素<br>
 * tag:表示根据标签查找元素,如tag:a,表示查找tag为a的第一个元素<br>
 * id:表示根据id查找元素,<br>
 * attribute:表示根据元素的属性查找元素,<br>
 * attr:表示根据属性的值获取最终数据<br>
 * text:表示获取元素中的纯文本数据<br>
 * html:表示获取元素中的所有数据，包括标签等<br>
 * @author	youaremoon
 * @version
 * @Date	Jul 13, 2014 12:44:47 PM
 */
public class JSoupDocAnalyse {
	protected static final String KEY_ID = "id";
	protected static final String KEY_CLASS = "class";
	protected static final String KEY_TAG = "tag";
	protected static final String KEY_ATTRIBUTE = "attribute";
	protected static final String[] ALLOW_KEYS = { KEY_ID, KEY_CLASS, KEY_TAG, KEY_ATTRIBUTE};
	
	protected static final String VALUE_TEXT = "text";
	protected static final String VALUE_HTML = "html";
	protected static final String VALUE_ATTR = "attr";
	protected static final String[] ALLOW_VALUES = { VALUE_TEXT, VALUE_HTML, VALUE_ATTR};
	
	static {
		Arrays.sort(ALLOW_KEYS);
		Arrays.sort(ALLOW_VALUES);
	}
	
	/**
	 * 获取元素的最终值
	 * @param ele
	 * @param detail
	 * @param throwException
	 * @return 
	 * String
	 */
	public static String getValue(Element ele, HtmlElementSingle detail, boolean throwException) {
		String key = detail.getKey();
		if (VALUE_TEXT.equals(key)) {
			return ele.text().trim();
		} else if (VALUE_HTML.equals(key)) {
			return ele.html().trim();
		} else if (VALUE_ATTR.equals(key)) {
			return ele.attr(detail.getValue()).trim();
		} else if (throwException) {
			throw new IllegalArgumentException("unknown key:" + key);
		} else {
			return null;
		}
	}
	
	/**
	 * 根据表达式获取单个元素
	 * @param ele
	 * @param expression
	 * @return 
	 * Element
	 */
	public static Element getElementByExp(Element ele, String expression) {
		return getElementByExp(ele, new HtmlElementExpressionParser(expression));
	}

	/**
	 * 根据表达式获取单个元素
	 * @param ele
	 * @param parser
	 * @return 
	 * Element
	 */
	public static Element getElementByExp(Element ele, HtmlElementExpressionParser parser) {
		Element lastChild = ele;
		while (parser.hasNext()) {
			HtmlElementSingle detail = (HtmlElementSingle) parser.next();

			String key = detail.getKey();
			String value = detail.getValue();
			if (KEY_ID.equals(key)) {
				lastChild = lastChild.getElementById(value);
			} else if (isAllowKey(key)) {
				lastChild = searchElement(lastChild, detail, true);
			}
		}

		return lastChild;
	}

	/**
	 * 通过制定的类型与值从制定元素中获取一组元素，如class:aClass为获取class名为aClass的元素
	 * 
	 * @param ele
	 * @param detail
	 * @return Element
	 */
	public static Element searchElement(Element ele, HtmlElementSingle detail, boolean throwException) {
		Elements searchElements = searchElements(ele, detail, throwException);
		if (searchElements == null) {
			return null;
		}
		
		return searchElementByIndex(searchElements, detail, throwException);
	}
	
	public static Element searchElementByIndex(Elements eles, HtmlElementSingle detail, boolean throwException) {
		int index = detail.getIndex();
		if (index >= 0) {
			if (eles.size() <= index) {
				if (throwException) {
					throw new ArrayIndexOutOfBoundsException(detail.getExpression() + " error at " + index);
				} else {
					return null;
				}
			}

			return eles.get(index);
		} else {
			return eles.get(0);
		}
	}

	/**
	 * 通过制定的类型与值从制定元素中查找一组元素，如class:aClass为获取class名为aClass的元素
	 * 
	 * @param ele
	 * @param detail
	 * @return Elements
	 */
	public static Elements searchElements(Element ele, HtmlElementSingle detail, boolean throwException) {
		String key = detail.getKey();
		String value = detail.getValue();

		if (KEY_CLASS.equals(key)) {
			return ele.getElementsByClass(value);
		} else if (KEY_TAG.equals(key)) {
			return ele.getElementsByTag(value);
		} else if (KEY_ATTRIBUTE.equals(key)) {
			return ele.getElementsByAttribute(value);
		} else if (throwException) {
			throw new IllegalArgumentException("unknown key:" + key);
		} else {
			return null;
		}
	}
	
	public static boolean isAllowKey(String key) {
		if (null == key) {
			return false;
		}
		
		return Arrays.binarySearch(ALLOW_KEYS, key) >= 0;
	}
	
	public static boolean isAllowValue(String key) {
		if (null == key) {
			return false;
		}
		
		return Arrays.binarySearch(ALLOW_VALUES, key) >= 0;
	}
}
