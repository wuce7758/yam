/**
 * ParseMethodConfig.java <br>
 * com.yam.base.html.parse.config <br>
 *
 * Function： TODO <br>
 *
 *   ver     date      		author		<br>
 * ──────────────────────────────────	<br>
 *   1.0	 Jul 13, 2014		youaremoon	<br>
 *
 * Copyright (c) 2013 YangBo, All Rights Reserved.<br>
 */
package com.yam.base.html.parse.config;

/**
 * Function: TODO<br>
 *
 * @author	youaremoon
 * @version
 * @Date	Jul 13, 2014 11:12:04 PM
 */
public class ParseMethodConfig extends ParseBaseConfig {
	private String methodName;
	
	public String toString() {
		return "methodName:" + methodName + "," + super.toString();
	}
	
	/**
	 * methodName
	 *
	 * @return  the methodName
	 * @since   1.0.0
	 */
	public String getMethodName() {
		return methodName;
	}
	/**
	 * @param methodName the methodName to set
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
}
