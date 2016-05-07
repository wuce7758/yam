/**
 * AbstractHtmlElementExpression.java <br>
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
 * Function: TODO<br>
 *
 * @author	youaremoon
 * @version
 * @Date	Jul 12, 2014 10:34:17 AM
 */
public abstract class AbstractHtmlElementExpression implements IHtmlElementExpression {
	private String expression;
	
	public AbstractHtmlElementExpression(String expression) {
		this.expression = expression;
	}
	
	/* (non-Javadoc)
	 * @see com.yam.base.html.expression.IHtmlElementExpression#getExpression()
	 */
	@Override
	public String getExpression() {
		return this.expression;
	}
	
	public String toString() {
		return this.expression;
	}
}
