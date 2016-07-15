package com.gerry.common.framework.vertx.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * vertx消息传递序列化方式
 * 
 *
 * @author gerry
 * @version  1.0, 2016年6月21日下午12:18:58
 * @since   com.gerry.link 1.0
 */
public class VertxFastJsonMessageConverter {

	public static <T> String converter(T obj) {
		return JSON.toJSONString(obj, SerializerFeature.DisableCircularReferenceDetect);
	}

	public static <T> T converter(String text, Class<T> clazz) {
		return (T) JSON.parseObject(text, clazz);
	}

	public static byte[] toBytes(Object obj){
		return JSON.toJSONBytes(obj, SerializerFeature.DisableCircularReferenceDetect);
	}
}
