package com.gerry.common.framework.vertx.core;

import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.Message;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import com.gerry.common.framework.vertx.utils.VertxEmptyUtils;
import com.google.common.collect.Maps;

/**
 * eventbus消息头处理工厂
 * 
 *
 * @author gerry
 * @version 1.0, 2016年7月14日下午6:53:52
 * @since com.gerry.link 1.0
 */
public interface EventBusHeaderFactory {

	public final static String EVENTBUS_HEAD = "method";

	static ConcurrentMap<String, HashMap<String, Method>> methods = Maps.newConcurrentMap();

	public static DeliveryOptions defaultHeader(DeliveryOptions options, String value) {
		options.addHeader(EVENTBUS_HEAD, value);
		return options;
	}

	static <T> String defaultHeader(Message<T> msg) {
		return msg.headers().get(EVENTBUS_HEAD);
	}

	static Map<String, Method> storage(String key) {
		if (VertxEmptyUtils.isEmpty(methods.get(key))) {
			methods.put(key, new HashMap<>());
		}
		return methods.get(key);
	}

	static void storage(String key, Map<String, Method> value) {
		if (VertxEmptyUtils.isEmpty(methods.get(key))) {
			methods.put(key, new HashMap<>());
		}
		methods.get(key).putAll(value);
	}
}
