package com.wuzl.lean.spring.data.redis.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * redis序列化 后期可根据需要做成配置
 * 
 * @author wuzl
 * 
 */
public class RedisSerializer {
	public static final String EMPTY_JSON = "{}";
	private static final Log log = LogFactory.getLog(RedisSerializer.class);

	/**
	 * 编码
	 * 
	 * @param object
	 * @return
	 */
	public static String seriazileAsString(Object object) {
		if (object == null) {
			return EMPTY_JSON;
		}
		return JSON.toJSONString(object);
	}

	/**
	 * 解码
	 * 
	 * @param object
	 * @return
	 */
	public static <T> T deserializeAsObject(String str, Class<T> clazz) {
		if (str == null || clazz == null) {
			return null;
		}
		try {
			return JSONObject.parseObject(str, clazz);
		} catch (Exception e) {
			log.error(new StringBuilder("解码失败,解码数据是:").append(str), e);
			return null;
		}
	}
}
