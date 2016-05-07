/**
 * HtmlElementExpressionParser.java <br>
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

import java.util.ArrayList;
import java.util.List;

/**
 * Function: html元素表达式解析<br>
 *
 * @author	youaremoon
 * @version
 * @Date	Jul 12, 2014 10:38:51 AM
 */
public class HtmlElementExpressionParser {
	private final String expression;
	private List<String> expressionList;
	private int expressionLength;
	private int current;

	public HtmlElementExpressionParser(String expression) {
		this.expression = expression;
		this.current = 0;
		this.expressionLength = 0;

		analyse();
	}

	private void analyse() {
		if (null == this.expression) {
			throw new java.lang.IllegalArgumentException("expression can not be null.");
		}

		this.expressionList = new ArrayList<String>();

		int lastFromIndex = 0;
		int length = this.expression.length();
		int splitStringLen = IHtmlElementExpression.SPLIT_ELEMENT_STRING.length();
		int index;
		while ((index = this.expression.indexOf(
				IHtmlElementExpression.SPLIT_ELEMENT_STRING, lastFromIndex)) != -1) {
			// 如果出现表达式组，则直接截断至末尾，并结束解析
			if (this.expression.charAt(lastFromIndex) == IHtmlElementExpression.SPLIT_ARRAY_START_CHAR) {
				this.addExpression(this.expression.substring(lastFromIndex));
				lastFromIndex = length;
				break;
			}
			
			if (lastFromIndex != index) {
				// 取出根据分隔符分割出来的元素
				String val = this.expression.substring(lastFromIndex, index);
				this.addExpression(val);
			}

			lastFromIndex = index + splitStringLen;
			if (lastFromIndex >= length - 1) {
				continue;
			}
		}

		if ((lastFromIndex >= 0) && (lastFromIndex != length)) {
			this.addExpression(this.expression.substring(lastFromIndex));
		}

		this.expressionLength = this.expressionList.size();
	}

	public boolean hasNext() {
		return this.current < this.expressionLength;
	}

	public IHtmlElementExpression next() {
		int index = current;
		this.current++;

		return get(index);
	}
	
	public IHtmlElementExpression last() {
		return get(expressionLength - 1);
	}
	
	private IHtmlElementExpression get(int i) {
		String val = this.expressionList.get(i);

		boolean isSingle = !HtmlElementMulti.isMulti(val);
		
		return HtmlElementExpressionFactory.getExpression(val, isSingle);
	}
	
	private void addExpression(String val) {
		val = val.trim();
		if (val.length() == 0) {
			return;
		}
		
		this.expressionList.add(val);
	}

	public void remove() {
	}
}
