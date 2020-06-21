/**
 * 
 */
package com.github.lly835.resp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;

/**
 * 基础数据实体类
 *
 */
public class BaseEntity implements Serializable {
	private static final ObjectMapper mapper = new ObjectMapper();
	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -4225260000145200619L;

	@Override
	public String toString() {
		return toJsonString(this);
	}
	/**
	 * 
	* @Description: 将Java对象序列化JSON字符串
	* @param @param obj
	* @return String
	* @throws
	 */
	public static String toJsonString(Object obj) {
		try {
			return mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
