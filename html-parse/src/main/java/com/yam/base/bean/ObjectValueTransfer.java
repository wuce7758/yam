/**
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   1.0	 2011-8-10 		youaremoon
 *
 * Copyright (c) 2011 YangBo, All Rights Reserved.
*/

package com.yam.base.bean;
/**
 * Function: TODO ADD FUNCTION
 *
 * @author   youaremoon
 * @version  
 * @since    Ver 1.1
 * @Date	 2011-8-10  上午09:18:00
 */
public class ObjectValueTransfer implements IValueTransfer<Object> {

	@Override
	public Object transferValue(Object value, Class<?> destClass) {
		return value;
	}

}

