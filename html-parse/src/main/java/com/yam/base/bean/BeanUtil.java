/**
 * BeanUtil.java <br>
 * com.yam.base.bean <br>
 *
 * Function： TODO <br>
 *
 *   ver     date      		author		<br>
 * ──────────────────────────────────	<br>
 *   1.0	 Jul 13, 2014		youaremoon	<br>
 *
 * Copyright (c) 2013 YangBo, All Rights Reserved.<br>
 */
package com.yam.base.bean;

import java.lang.reflect.Method;

/**
 * Function: TODO<br>
 *
 * @author	youaremoon
 * @version
 * @Date	Jul 13, 2014 10:06:20 PM
 */
public class BeanUtil {
	public static final INameTransfer DEFAULT_NAME_TRANSFER = new DefaultNameTransfer();
	public static final IValueTransfer<String> STRING_VALUE_TRANSFER = new StringValueTransfer();
	public static final IValueTransfer<Object> Object_VALUE_TRANSFER = new ObjectValueTransfer();
	
	public static <T> boolean setValueToObject(IValueTransfer<T> valueTransfer, 
			Object obj, Method[] ms, String methodName, T value) throws Exception {
		return setValueToObject(DEFAULT_NAME_TRANSFER, valueTransfer, obj, ms, methodName, value);
	}
	
	public static boolean setStringValueToObject(Object obj, Method[] ms, String methodName, String value) throws Exception {
		return setValueToObject(DEFAULT_NAME_TRANSFER, STRING_VALUE_TRANSFER, obj, ms, methodName, value);
	}
	
	public static boolean setObjectValueToObject(Object obj, Method[] ms, String methodName, Object value) throws Exception {
		return setValueToObject(DEFAULT_NAME_TRANSFER, Object_VALUE_TRANSFER, obj, ms, methodName, value);
	}
	
	/**
	 * 设置值到对象
	 * @param nameTransfer	名称转换
	 * @param valueTransfer 值转换
	 * @param obj	需要被设置的对象
	 * @param ms	从指定方法中查找所需的设置方法
	 * @param methodName 未处理的方法名，需要经过nameTransfer转换
	 * @param value	需要设置的值
	 * @return
	 */
	public static <T> boolean setValueToObject(INameTransfer nameTransfer,
			IValueTransfer<T> valueTransfer, Object obj, Method[] ms,
			String methodName, T value) throws Exception {
		//获取设置方法的名称
		String setMethodName = nameTransfer.getSetMethod(methodName);

		Class<?> setParameterClass = null;
		Method setMethod = null;
		for(int i = 0; i < ms.length; i++) {
			Method m = ms[i];
			if(m.getName().equals(setMethodName)) {
				Class<?>[] mClasses = m.getParameterTypes();
				if(mClasses.length == 1) {
					setMethod = m;
					setParameterClass = mClasses[0];
					break;
				}
			}
		}

		if (null != setMethod) {
			setMethod.invoke(obj,
					valueTransfer.transferValue(value, setParameterClass));
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 设置值到对象
	 * @param nameTransfer	名称转换
	 * @param valueTransfer 值转换
	 * @param obj	需要被设置的对象
	 * @param methodName 未处理的方法名，需要经过nameTransfer转换
	 * @param value	需要设置的值
	 * @return
	 */
	public static <T> boolean setValueToObject(INameTransfer nameTransfer,
			IValueTransfer<T> valueTransfer, Object obj, String methodName,
			T value) throws Exception {
		Class<?> tClass = obj.getClass();

		// 通过get方法获取参数类型
		Class<?> setParameterClass = String.class; // 默认为String类型
		String getMethodName = nameTransfer.getGetMethod(methodName);
		Method getMethod = tClass.getMethod(getMethodName, new Class<?>[] {});
		if (null != getMethod) {
			setParameterClass = getMethod.getReturnType();
		}

		// 调用对应方法进行设置
		String setMethodName = nameTransfer.getSetMethod(methodName);
		Method setMethod = tClass.getMethod(setMethodName, setParameterClass);
		if (null != setMethod) {
			setMethod.invoke(obj,
					valueTransfer.transferValue(value, setParameterClass));
			return true;
		} else {
			return false;
		}
	}
}
