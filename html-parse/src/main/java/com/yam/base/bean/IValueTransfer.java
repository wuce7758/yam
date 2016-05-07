/**
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   1.0	 2011-8-3 		youaremoon
 *
 * Copyright (c) 2011 YangBo, All Rights Reserved.
*/

package com.yam.base.bean;
/**
 * Function: 将指定的值装换为指定类的值
 *
 * @author   youaremoon
 * @version  
 * @since    Ver 1.1
 * @Date	 2011-8-3  下午04:50:35
 */
public interface IValueTransfer<T> {
	Object transferValue(T value, Class<?> destClass);
}

