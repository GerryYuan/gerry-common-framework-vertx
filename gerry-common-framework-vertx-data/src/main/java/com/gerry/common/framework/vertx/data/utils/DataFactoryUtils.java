package com.gerry.common.framework.vertx.data.utils;

import io.vertx.redis.RedisClient;

import com.gerry.common.framework.vertx.data.factory.DataOptions;
import com.gerry.common.framework.vertx.utils.VertxCoreUtils;

public interface DataFactoryUtils {

	static RedisClient REDISCLIENT = (RedisClient) DataOptions.newData().createClient(VertxCoreUtils.vertx);

}
