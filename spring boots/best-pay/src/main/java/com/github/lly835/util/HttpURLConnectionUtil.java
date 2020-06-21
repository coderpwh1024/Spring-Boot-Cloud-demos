package com.github.lly835.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

/**
 * 远程请求工具类，采用原生的HttpURLConnection进行封装工具方法
 */
@Slf4j
public class HttpURLConnectionUtil {

	private static final String METHOD_GET = "GET";

	private static final String METHOD_POST = "POST";

	private static final String UTF8 = "UTF-8";

	private static final String CHARSET = "charset";

	private static final String ACCEPT = "accept";

	private static final String ACCEPT_PATTERN = "*/*";

	private static final String CONNECTION = "connection";

	private static final String KEEP_ALIVE = "Keep-Alive";

	private static final String CONTENT_TYPE = "Content-Type";

	private static final String CONTENT_TYPE_JSON = "application/json;charset=UTF-8";

	private static final String EQUAL = "=";

	private static final String COMMA_AND = "&";

	private static final String COMMA_QUESTION = "?";
	
	/***
	 * 超时时间：30s
	 */
	private static final int TIME_OUT = 30 * 1000;

	public static String doPost(String url, JSONObject jsonParamObject) {
		return request(url, jsonParamObject, METHOD_POST);
	}
	
	public static String doGet(String url, JSONObject jsonParamObject) {
		return request(url, jsonParamObject, METHOD_GET);
	}
	
	private static String request(String url, JSONObject jsonParamObject, String method) {
		log.info("-->request-->请求接口：" + url + ", 参数：" + jsonParamObject + ", 请求方式：" + method);
		OutputStream out = null;
		BufferedReader bReader = null;
		String line, result = "";
		URL realURL;
		try {
			if (METHOD_GET.equalsIgnoreCase(method) && jsonParamObject != null) {
				Set<String> keys = jsonParamObject.keySet();
				StringBuilder sb = new StringBuilder();
				for (String key : keys) {
					sb.append(key);
					sb.append(EQUAL);
					sb.append(jsonParamObject.get(key));
					sb.append(COMMA_AND);
				}
				url = url + COMMA_QUESTION + sb.toString();
			}
			realURL = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) realURL.openConnection();
			conn.setRequestMethod(method);
			conn.setReadTimeout(TIME_OUT);
			conn.setConnectTimeout(TIME_OUT);
			conn.setRequestProperty(CHARSET, UTF8);
			conn.setRequestProperty(ACCEPT, ACCEPT_PATTERN);
			conn.setRequestProperty(CONNECTION, KEEP_ALIVE);
			conn.setRequestProperty(CONTENT_TYPE, CONTENT_TYPE_JSON);
			conn.setAllowUserInteraction(false);
			conn.setUseCaches(false);
			conn.setDoInput(true);
			if (jsonParamObject != null && METHOD_POST.equalsIgnoreCase(method)) {
				conn.setDoOutput(true);
				out = new DataOutputStream(conn.getOutputStream());
				out.write(jsonParamObject.toString().getBytes(UTF8));
				out.flush();
			}
			bReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while (null != (line = bReader.readLine())) {
				result += line;
			}
		} catch (Exception e) {
			log.error("-->request-->请求发生异常：", e);
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (bReader != null) {
					bReader.close();
				}
			} catch (IOException e) {
				log.error("-->request-->关闭流发生异常：", e);
			}

		}
		log.info("-->request--> 响应：" + result);
		return result;
	}
}
