/**
 * HtmlElementMulti.java <br>
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

import com.yam.base.string.StringUtil;

/**
 * Function: 多个html元素<br>
 * 
 * @author youaremoon
 * @version
 * @Date Jul 12, 2014 10:24:57 AM
 */
public class HtmlElementMulti extends AbstractHtmlElementExpression {
	private List<HtmlElementSingle> detailList;

	public HtmlElementMulti(String expression) {
		super(expression);
		
		if (!isMulti(expression)) {
			throw new IllegalArgumentException("expression must start with [ and end with ]:" + expression);
		}
		
		String[] secs = StringUtil.split(expression.substring(1, expression.length() - 1), SPLIT_MULTI_EXP);
	    for (int i = 0; i < secs.length; i++) {
	      addDetail(new HtmlElementSingle(secs[i]));
	    }
	}

	public void addDetail(HtmlElementSingle detail) {
		if (detail == null) {
			return;
		}

		if (this.detailList == null) {
			this.detailList = new ArrayList<HtmlElementSingle>();
		}

		this.detailList.add(detail);
	}

	public List<HtmlElementSingle> getDetailList() {
		return this.detailList;
	}

	public void setDetailList(List<HtmlElementSingle> detailList) {
		this.detailList = detailList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yam.base.html.expression.IHtmlElementExpression#isSingle()
	 */
	@Override
	public boolean isSingle() {
		return false;
	}
	
	/**
	 * 是否是含多个元素的元素
	 * @param expression
	 * @return 
	 * boolean
	 */
	public static boolean isMulti(String expression) {
		if ((expression.startsWith(SPLIT_ARRAY_START))
				&& (expression.endsWith(SPLIT_ARRAY_END))) {
			return true;
		} else {
			return false;
		}
	}
}
