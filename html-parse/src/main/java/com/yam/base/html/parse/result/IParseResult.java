/**
 * IParseResult.java <br>
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
package com.yam.base.html.parse.result;

import org.jsoup.nodes.Document;

import com.yam.base.html.parse.config.ParseBeanConfig;

/**
 * Function: TODO<br>
 *
 * @author	youaremoon
 * @version
 * @Date	Jul 14, 2014 6:19:37 PM
 */
public interface IParseResult {
	Object getResult(Document doc, ParseBeanConfig beanConfig) throws Exception;
}
