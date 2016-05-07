/**
 * XmlBeanConfigFactory.java <br>
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

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

import com.yam.base.config.ConfigReader;


/**
 * Function: bean配置读取工厂<br>
 *
 * @author	youaremoon
 * @version
 * @Date	Jul 13, 2014 10:34:52 PM
 */
public class XmlBeanConfigFactory {
	/**
	 * 保存所有配置文件,避免多次读取
	 */
	private static final ConcurrentHashMap<String, XmlBeanConfigReader> xmlReaderMap = new ConcurrentHashMap<String, XmlBeanConfigReader>();
	
	/**
     * 获取指定XML配置信息实例
     * @param filePath 配置文件路径
     */
    public static XmlBeanConfigReader getXmlConfig(String filePath) {
        return getXmlConfig(new File(filePath));
    }
    
    /**
     * 获取指定XML配置信息实例
     * @param file 配置文件
     */
    public static XmlBeanConfigReader getXmlConfig(File file) {
    	return getXmlConfig(file, ConfigReader.ONETIME_RELOAD);
    }
    
    /**
     * 获取指定XML配置信息实例
     * @param file 配置文件
     * @param reloadMode 文件信息重新加载的模式
     */
    public static XmlBeanConfigReader getXmlConfig(File file, int reloadMode) {
    	String filePath = file.getAbsolutePath();
    	
    	//防止多次读取
    	XmlBeanConfigReader reader = xmlReaderMap.get(filePath);
    	if(null == reader) {
    		reader = new XmlBeanConfigReader(file, reloadMode);
    		XmlBeanConfigReader oldReader = xmlReaderMap.putIfAbsent(filePath, reader);
    		if(null != oldReader) {
    			reader = oldReader;
    		}
    	}
	     
	    return reader;
    }
}
