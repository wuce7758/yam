/**
 * JSoupUtil.java <br>
 * com.yam.base.html.jsoup <br>
 *
 * Function： TODO <br>
 *
 *   ver     date      		author		<br>
 * ──────────────────────────────────	<br>
 *   1.0	 Jul 12, 2014		youaremoon	<br>
 *
 * Copyright (c) 2013 YangBo, All Rights Reserved.<br>
 */
package com.yam.base.html.jsoup;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Function: jsoup工具类<br>
 * 
 * @author youaremoon
 * @version
 * @Date Jul 12, 2014 11:21:51 AM
 */
public class JSoupUtil {
	private static final Logger logger = LoggerFactory.getLogger(JSoupUtil.class);
	private static final int DEFAULT_CONNECT_TIMEOUT = 10000;
	private static final Map<String, String> DEFAULT_HEADER = new HashMap<String, String>();
	static {
		DEFAULT_HEADER.put("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36");
	}

	/**
	 * 尝试指定次数的抓取
	 * 
	 * @param url
	 * @param times
	 *            抓取尝试的次数，若该值小于等于0则无限抓取
	 * @return
	 * @throws IOException
	 *             Document
	 */
	public static Document parseWithRetry(String url, int times)
			throws IOException {
		return parseWithRetry(url, times, DEFAULT_CONNECT_TIMEOUT, DEFAULT_HEADER);
	}

	/**
	 * 尝试指定次数的抓取
	 * 
	 * @param url
	 * @param times 抓取尝试的次数，若该值小于等于0则无限抓取
	 * @param timeout 每次抓取的超时时间
	 * @param headers 抓取使用的http头
	 * @return Document
	 * @throws IOException          
	 */
	public static Document parseWithRetry(String url, int times, int timeout,
			Map<String, String> headers) throws IOException {
		if (times <= 0) {
			times = Integer.MAX_VALUE;
		}

		IOException lastException = null;
		for (int i = 0; i < times; i++) {
			try {
				if (i > 0) {
					logger.debug("{} retry {} times...", url, i);
				}

				return parse(url, timeout, headers);
			} catch (IOException ex) {
				lastException = ex;
			}
		}

		throw lastException;
	}

	public static Document parse(String url) throws IOException {
		return parse(url, DEFAULT_CONNECT_TIMEOUT, DEFAULT_HEADER);
	}

	public static Document parse(String url, int timeout,
			Map<String, String> headers) throws IOException {
		Connection conn = Jsoup.connect(url);
		conn.timeout(timeout);

		if (null != headers) {
			for (String key : headers.keySet()) {
				conn.header(key, headers.get(key));
			}
		}

		return conn.get();
	}
}
