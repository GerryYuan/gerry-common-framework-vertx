package com.gerry.common.web.test;

import lombok.extern.log4j.Log4j;

import com.gerry.common.framework.vertx.data.utils.DataFactoryUtils;

@Log4j
public class TestRedis {

	public static void main(String[] args) {
		DataFactoryUtils.REDISCLIENT.set("user.list", "444444", res -> {
			if (res.succeeded()) {
				log.info(res.result());
			} else {
				log.error(res.cause());
			}
		});
	}
}
