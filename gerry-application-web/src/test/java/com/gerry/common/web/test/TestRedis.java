package com.gerry.common.web.test;

import com.gerry.common.framework.vertx.data.factory.redis.RedisManager;

public class TestRedis {

	public static void main(String[] args) {
		RedisManager.save("redis.key", "XXXXXXX");
	}
}
