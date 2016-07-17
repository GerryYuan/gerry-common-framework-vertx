package com.gerry.common.framework.vertx.data.factory.redis;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

import java.util.List;

import lombok.extern.log4j.Log4j;

import com.gerry.common.framework.vertx.data.utils.DataFactoryUtils;

@Log4j
public class RedisManager {

	public static void save(String key, String value) {
		DataFactoryUtils.REDISCLIENT.set(key, value, res -> {
			if (res.failed()) {
				log.error(" save to redis failed , key -> " + key + ", value -> " + value);
			}
		});
	}

	public static void save(String key, String value, long seconds) {
		DataFactoryUtils.REDISCLIENT.setex(key, seconds, value, res -> {
			if (res.failed()) {
				log.error(" save to redis failed , key -> " + key + ", value -> " + value);
			}
		});
	}

	public static void delete(String key) {
		DataFactoryUtils.REDISCLIENT.del(key, res -> {
			if (res.failed()) {
				log.error(" delete to redis failed , key -> " + key);
			}
		});
	}

	public static void delete(List<String> keys) {
		DataFactoryUtils.REDISCLIENT.delMany(keys, res -> {
			if (res.failed()) {
				log.error(" delete to redis failed , keys -> " + keys.toArray());
			}
		});
	}

	public static void exist(String key, Handler<AsyncResult<Long>> handler) {
		DataFactoryUtils.REDISCLIENT.exists(key, handler);
	}

}
