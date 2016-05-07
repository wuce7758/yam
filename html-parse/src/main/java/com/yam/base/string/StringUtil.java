/**
 * StringUtil.java <br>
 * com.yam.base.string <br>
 *
 * Function： TODO <br>
 *
 *   ver     date      		author		<br>
 * ──────────────────────────────────	<br>
 *   1.0	 Jul 12, 2014		youaremoon	<br>
 *
 * Copyright (c) 2013 YangBo, All Rights Reserved.<br>
 */
package com.yam.base.string;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Function: 字符串操作工具栏<br>
 *
 * @author	youaremoon
 * @version
 * @Date	Jul 12, 2014 10:55:42 AM
 */
public final class StringUtil {
	/**
	 * 空的String数组
	 */
	public static final String[] EMPTY_ARRAY = new String[0];

	/**
	 * 验证字符串是否为空或null
	 * @param msg
	 * @return boolean
	*/
	public static boolean isNullOrEmpty(String msg) {
		return null == msg || msg.length() == 0;
	}
	
	/**
	 * 填充指定字符到字符串左边到指定长度
	 * @param orignalValue
	 * @param padChar	需要填充的字符
	 * @param length	填充后的长度
	 * @return
	 */
	public static String padLeft(String orignalValue, char padChar, int length) {
		return pad(orignalValue, padChar, length, true);
	}

	/**
	 * 填充指定字符到字符串右边到指定长度
	 * @param orignalValue
	 * @param padChar	需要填充的字符
	 * @param length	填充后的长度
	 * @return
	 */
	public static String padRight(String orignalValue, char padChar, int length) {
		return pad(orignalValue, padChar, length, false);
	}

	/**
	 * 填充指定字符到字符串的指定位置知道达到指定长度
	 * @param orignalValue
	 * @param padChar	需要填充的字符
	 * @param length	填充后的长度
	 * @param isLeft	是否填充在左边
	 * @return
	 */
	public static String pad(String orignalValue, char padChar, int length, boolean isLeft) {
		//null作为空字符串
		if(null == orignalValue) {
			orignalValue = "";
		}

		//判断长度是否比需要的长度短，只有在短的情况下才会进行处理
		int oldLen = orignalValue.length();
		if(oldLen >= length) {
			return orignalValue;
		} else {
			int padLen = length - oldLen;
			//得到需要添加的字符
			char[] appendChars = new char[padLen];
			Arrays.fill(appendChars, padChar);

			char[] newChars = new char[length];
			char[] orignalChars = orignalValue.toCharArray();
			if(isLeft) {
				System.arraycopy(appendChars, 0, newChars, 0, padLen);
				System.arraycopy(orignalChars, 0, newChars, padLen, oldLen);
			} else {
				System.arraycopy(orignalChars, 0, newChars, 0, oldLen);
				System.arraycopy(appendChars, 0, newChars, oldLen, padLen);
			}

			return new String(newChars);
		}
	}

	public static String subString(String text, int start, int length) {
		if(null == text || text.length() == 0) {
			return text;
		}

		int endPos = text.length();
		//开始位置大于总长度，则返回空
		if(start >= endPos) {
			return text;
		}

		//
		if(start + length > endPos) {
			length = endPos - start;
		}

		return text.substring(start, start + length);
	}

	/**
	 * 按照指定长度进行截取
	 * @param
	 * @return
	 */
	public static String getStringInLength(String value, int length) {
		if(null == value || value.length() <= length) {
			return value;
		} else {
			return value.substring(0, length);
		}
	}
	
	/**
	 * 直接针对String的split，不支持正则表达式
	 * @param
	 * @return
	 */
	public static String[] split(String value, char splitChar) {
		if(null == value) {
			return null;
		}

		int len = value.length();
		if(len == 0) {
			return EMPTY_ARRAY;
		}

		int lastFromIndex = 0; //最近一次查询的其实位置
		int index;
		ArrayList<String> result = new ArrayList<String>();
		while((index = value.indexOf(splitChar, lastFromIndex)) != -1) {
			if(lastFromIndex != index) {
				result.add(value.substring(lastFromIndex, index));
			}

			lastFromIndex = index + 1;
		}

		if(lastFromIndex >= 0 && lastFromIndex != len) {
			result.add(value.substring(lastFromIndex));
		}

		return result.toArray(new String[result.size()]);
	}

	/**
	 * 直接针对String的split，不支持正则表达式
	 * @param
	 * @return
	 */
	public static String[] split(String value, String splitString) {
		if(null == value) {
			return null;
		}

		int len = value.length();
		if(len == 0) {
			return EMPTY_ARRAY;
		}

		int splitStringLen = splitString.length();
		if(splitStringLen == 0) {
			return new String[] { value };
		}

		int lastFromIndex = 0; //最近一次查询的其实位置
		int index;
		ArrayList<String> result = new ArrayList<String>();
		while((index = value.indexOf(splitString, lastFromIndex)) != -1) {
			if(lastFromIndex != index) {
				result.add(value.substring(lastFromIndex, index));
			}

			lastFromIndex = index + splitStringLen;
		}

		if(lastFromIndex >= 0 && lastFromIndex != len) {
			result.add(value.substring(lastFromIndex));
		}

		return result.toArray(new String[result.size()]);
	}
}
