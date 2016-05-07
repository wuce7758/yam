/**
 * ParseResultFactory.java <br>
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

/**
 * Function: TODO<br>
 *
 * @author	youaremoon
 * @version
 * @Date	Jul 14, 2014 6:20:19 PM
 */
public class ParseResultFactory {
	public static IParseResult get(String className) {
		if ("map".equals(className)) {
			return new MapParseResult();
		} else if ("json".equals(className)) {
			return null;
		}
		
		return new ObjectParseResult();
	}
}
