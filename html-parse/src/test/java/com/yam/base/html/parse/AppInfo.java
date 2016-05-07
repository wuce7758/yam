/**
 * AppInfo.java <br>
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
package com.yam.base.html.parse;

import java.util.List;

/**
 * Function: TODO<br>
 *
 * @author	youaremoon
 * @version
 * @Date	Jul 14, 2014 9:55:59 PM
 */
public class AppInfo {
	private String name;
	private String packageName;
	private String categoryName;
	private String tag;
	private List<String> picList;

	public String toString() {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("name:").append(name)
			.append(",packageName:").append(packageName)
			.append(",categoryName:").append(categoryName)
			.append(",tag:").append(tag)
			.append(",picList:").append(picList);
		
		return strBuilder.toString();
	}
	
	/**
	 * picList
	 *
	 * @return  the picList
	 * @since   1.0.0
	 */
	public List<String> getPicList() {
		return picList;
	}

	/**
	 * @param picList the picList to set
	 */
	public void setPicList(List<String> picList) {
		this.picList = picList;
	}

	/**
	 * name
	 *
	 * @return  the name
	 * @since   1.0.0
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * packageName
	 *
	 * @return  the packageName
	 * @since   1.0.0
	 */
	public String getPackageName() {
		return packageName;
	}
	/**
	 * @param packageName the packageName to set
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	/**
	 * categoryName
	 *
	 * @return  the categoryName
	 * @since   1.0.0
	 */
	public String getCategoryName() {
		return categoryName;
	}
	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	/**
	 * tag
	 *
	 * @return  the tag
	 * @since   1.0.0
	 */
	public String getTag() {
		return tag;
	}
	/**
	 * @param tag the tag to set
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}
	
}
