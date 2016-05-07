/**
 * HtmlExpressionValueParse.java <br>
 * com.yam.base.html.parse <br>
 *
 * Function： TODO <br>
 *
 *   ver     date      		author		<br>
 * ──────────────────────────────────	<br>
 *   1.0	 Jul 14, 2014		youaremoon	<br>
 *
 * Copyright (c) 2013 YangBo, All Rights Reserved.<br>
 */
package com.yam.base.html.parse.value;

import java.util.List;

import org.jsoup.nodes.Element;

import com.yam.base.html.jsoup.MultiGroupValueAnalyse;
import com.yam.base.html.jsoup.MultiValueAnalyse;
import com.yam.base.html.jsoup.SingleValueAnalyse;
import com.yam.base.html.parse.config.ParseMethodConfig;

/**
 * Function: TODO<br>
 *
 * @author	youaremoon
 * @version
 * @Date	Jul 14, 2014 2:10:43 PM
 */
public class HtmlExpressionValueParse implements IHtmlValueParse {

	/* (non-Javadoc)
	 * @see com.yam.base.html.parse.IHtmlValueParse#getValue(com.yam.base.html.parse.config.ParseMethodConfig, org.jsoup.nodes.Element)
	 */
	@Override
	public String getValue(ParseMethodConfig methodConfig, Element ele) {
		// TODO Auto-generated method stub
		return SingleValueAnalyse.getValueByExp(ele, methodConfig.getExpression());
	}

	/* (non-Javadoc)
	 * @see com.yam.base.html.parse.IHtmlValueParse#getValues(com.yam.base.html.parse.config.ParseMethodConfig, org.jsoup.nodes.Element)
	 */
	@Override
	public List<String> getValues(ParseMethodConfig methodConfig, Element ele) {
		// TODO Auto-generated method stub
		return MultiValueAnalyse.getValueListByExp(ele, methodConfig.getExpression());
	}

	/* (non-Javadoc)
	 * @see com.yam.base.html.parse.IHtmlValueParse#getValueGroup(com.yam.base.html.parse.config.ParseMethodConfig, org.jsoup.nodes.Element)
	 */
	@Override
	public List<String[]> getValueGroup(ParseMethodConfig methodConfig,
			Element ele) {
		return MultiGroupValueAnalyse.getGroupValueByExp(ele, methodConfig.getExpression());
	}
}
