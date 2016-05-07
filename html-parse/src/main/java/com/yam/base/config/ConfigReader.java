/**
 * ConfigReader.java
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   1.0	 2012-2-20 		youaremoon
 *
 * Copyright (c) 2012 YangBo, All Rights Reserved.
*/

package com.yam.base.config;

import java.io.File;
import java.util.Map;

import com.yam.base.string.StringUtil;

/**
 * ClassName:ConfigReader <br>
 * Function: 配置文件读取 <br>
 *
 * @author   youaremoon
 * @version  
 * @since    Ver 1.1
 * @Date	 2012-2-20  下午04:54:51
 */
public abstract class ConfigReader<T> {
	
	/**
     * 实时判断文件是否有改动
     */
    public static final int REALTIME_RELOAD = 0;
    
    /**
     * 每隔一段时间判断是否有改变
     */
    public static final int INTERVEL_RELOAD = 1;
    
    /**
     * 只加载一次
     */
    public static final int ONETIME_RELOAD = 2;
    
    private File file; 				//配置文件所在路径
    private long lastModifyTime = 0; 	//配置文件最后一次的修改时间
    private long lastLoadTime = 0;		//最后一次读取配置文件的时间 
    private int reloadMode;				//重新读取文件的模式
    private long interval = 5 * 60 * 1000;	//重新读取文件的时间间隔，间隔读取的方式有效
    private boolean isChanged = true;	//配置文件是否发生更改
    private Map<String, T> configMap; //配置存放的map
    
    public ConfigReader() {
    }
    
    public ConfigReader(String filePath) {
    	this(new File(filePath));
    }
    
    public ConfigReader(File file) {
    	this.file = file;
    }
    
    /**
     * 获取所有配置信息
     * @return
     */
    public Map<String, T> getConfigMap() {
    	initialConfig();
    	return configMap;
    }
    
    /**
     * 获取配置内容
     * @param key
     */
    public T getConfig(String key) {
        if (StringUtil.isNullOrEmpty(key)) {
            return null;
        }

        initialConfig();
        if (null == configMap) {
            return null;
        }

        return configMap.get(key);
    }
    
    /**
     * 初始化配置,如果最后修改时间与最后读取配置文件的时间不一致,则重新读取
     */
    public void initialConfig() {
        if (!needReload()) {
        	isChanged = false;
            return;
        }
        
        reset();
        configMap = readConfig();
    }
    
    /**
     * 读取配置文件内容
     */
    protected abstract Map<String, T> readConfig();
    
    /**
     * 重置数据
     */
    public void reset() {
    	isChanged = true;
    	
    	if(null != configMap) {
    		configMap.clear();
    	}
    }

	/**
	 * 获取文件是否发生更改
	 * @return  如果更改返回true,否则返回false
	 * @since   Ver 1.0
	 */
	public boolean isChanged() {
		return isChanged;
	}
    
    /**
     * 是否需要重新加载数据
     * @param 
     * @return
     */
    protected boolean needReload() {
    	switch(reloadMode) {
    	case REALTIME_RELOAD:
    		return needReloadRealtime();
    	case INTERVEL_RELOAD:
    		return needReloadInterval();
    	case ONETIME_RELOAD:
    		return needReloadOnetime();
    	default:
    		return needReloadRealtime();
    	}
    }
    
    //实时数据加载判断
    protected boolean needReloadRealtime() {
    	if (!file.exists()) {
    		return true;
    	}
    	
    	long fileModifyTime = file.lastModified();
        if (fileModifyTime == lastModifyTime) {
            return false;
        }
        
        lastModifyTime = fileModifyTime;
        
        return true;
    }
    
    //实时数据加载判断
    protected boolean needReloadInterval() {
    	long now = System.currentTimeMillis();
    	if(now - lastLoadTime > interval) {
    		lastLoadTime = now;
    		return needReloadRealtime();
    	} else {
    		return false;
    	}
    }
    
    //只读一次
    protected boolean needReloadOnetime() {
    	if(lastLoadTime <= 0) {
    		lastLoadTime = System.currentTimeMillis();
    		return true;
    	} else {
    		return false;
    	}
    }
    
	/**
	 * 获取file
	 * @return  the file
	 * @since   Ver 1.0
	 */
	public File getFile() {
		return file;
	}
	/**
	 * 设置file
	 * @param   file    
	 * @since   Ver 1.0
	 */
	public void setFile(File file) {
		this.file = file;
	}
	/**
	 * 获取lastModifyTime
	 * @return  the lastModifyTime
	 * @since   Ver 1.0
	 */
	public long getLastModifyTime() {
		return lastModifyTime;
	}
	/**
	 * 获取lastLoadTime
	 * @return  the lastLoadTime
	 * @since   Ver 1.0
	 */
	public long getLastLoadTime() {
		return lastLoadTime;
	}

	/**
	 * 获取reloadMode
	 * @return  the reloadMode
	 * @since   Ver 1.0
	 */
	public int getReloadMode() {
		return reloadMode;
	}
	/**
	 * 设置reloadMode
	 * @param   reloadMode    
	 * @since   Ver 1.0
	 */
	public void setReloadMode(int reloadMode) {
		this.reloadMode = reloadMode;
	}
	/**
	 * 获取重新读取配置的时间间隔,毫秒为单位
	 * @return  the interval
	 * @since   Ver 1.0
	 */
	public long getInterval() {
		return interval;
	}
	/**
	 * 设置重新读取配置的时间间隔,毫秒为单位
	 * @param   interval    
	 * @since   Ver 1.0
	 */
	public void setInterval(long interval) {
		this.interval = interval;
	}
}

