/**
 * HtmlElementSingle.java <br>
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

import com.yam.base.string.StringUtil;

/**
 * Function: 单个html元素<br>
 * 元素的组成为 key[_index]:value, 如果class:css0,class_9:css9
 * 
 * @author youaremoon
 * @version
 * @Date Jul 12, 2014 10:24:57 AM
 */
public class HtmlElementSingle extends AbstractHtmlElementExpression {

	private static final char SPLIT_KV_CHAR = ':';
	private static final char SPLIT_KI_CHAR = '_';

	private final String key;
	private final String value;
	private int index;

	public HtmlElementSingle(String expression) {
		super(expression);
		String[] secs = StringUtil.split(expression, SPLIT_KV_CHAR);

		String secs0 = secs[0];
		int splitIndex = secs0.indexOf(SPLIT_KI_CHAR);
		this.index = 0;
		if (splitIndex > 0) {
			this.key = secs0.substring(0, splitIndex);
			if (splitIndex < secs0.length() - 1) {
				this.index = Integer.parseInt(secs0.substring(splitIndex + 1));
			}
		} else {
			this.key = secs0;
		}

		this.value = (secs.length < 2 ? null : secs[1]);
	}

	public String getKey() {
		return this.key;
	}

	public String getValue() {
		return this.value;
	}

	public int getIndex() {
		return this.index;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yam.base.html.expression.IHtmlElementExpression#isSingle()
	 */
	@Override
	public boolean isSingle() {
		return true;
	}
}
