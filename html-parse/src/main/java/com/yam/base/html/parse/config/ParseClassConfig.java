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
public class ParseClassConfig extends ParseBaseConfig {
	private String className;

	/**
	 * className
	 *
	 * @return  the className
	 * @since   1.0.0
	 */
	public String getClassName() {
		return className;
	}


	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}


	public String toString() {
		return "className:" + className + "," + super.toString();
	}
}
