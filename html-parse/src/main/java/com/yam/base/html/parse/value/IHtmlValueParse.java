/**
 * IHtmlValueParse.java <br>
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

import com.yam.base.html.parse.config.ParseMethodConfig;

/**
 * Function: TODO<br>
 *
 * @author	youaremoon
 * @version
 * @Date	Jul 14, 2014 12:51:00 AM
 */
public interface IHtmlValueParse {
	String getValue(ParseMethodConfig methodConfig, Element ele);
	
	List<String> getValues(ParseMethodConfig methodConfig, Element ele);
	
	List<String[]> getValueGroup(ParseMethodConfig methodConfig, Element ele);
}
