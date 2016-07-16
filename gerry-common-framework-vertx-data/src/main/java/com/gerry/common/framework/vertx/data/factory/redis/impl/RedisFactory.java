package com.gerry.common.framework.vertx.data.factory.redis.impl;

import io.vertx.core.Vertx;
import io.vertx.redis.RedisClient;
import io.vertx.redis.RedisOptions;

import com.gerry.common.framework.vertx.configuration.VertxConfiguration;
import com.gerry.common.framework.vertx.core.Factory;
import com.gerry.common.framework.vertx.data.factory.DataFactory;
import com.gerry.common.framework.vertx.utils.VertxEmptyUtils;

/**
 * redis工厂
 * 
 *
 * @author gerry
 * @version 1.0, 2016年7月16日下午3:49:18
 * @since com.gerry.link 1.0
 */
public class RedisFactory implements DataFactory<RedisClient> {

	private DataFactory<RedisClient> dataFactory;

	private RedisClient redisClient;
	
	@Override
	public Factory newFactory() {
		if (!VertxEmptyUtils.isEmpty(dataFactory)) {
			return dataFactory;
		}
		return new RedisFactory();
	}

	@Override
	public RedisClient createClient(Vertx vertx) {
		if(VertxEmptyUtils.isNotEmpty(redisClient)){
			return redisClient;
		}
		return RedisClient.create(vertx, configuration());
	}

	private RedisOptions configuration() {
		if (!VertxConfiguration.existsJsonFile("redis.json")) {
			return new RedisOptions();
		}
		return new RedisOptions().setAddress(VertxConfiguration.redisStr("redis.address")).setPort(VertxConfiguration.redisInt("redis.port"));
	}

}
