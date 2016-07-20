package com.gerry.common.framework.vertx.rpc.verticle;

import io.vertx.core.AbstractVerticle;
import lombok.extern.log4j.Log4j;

import com.gerry.common.framework.eventbus.handler.EventBusHandlersFactory;

@Log4j
public class StandardRPCServiceVerticle extends AbstractVerticle {

	@Override
	public void start() throws Exception {
		// 接受，启动时需要注册
		EventBusHandlersFactory.registerHandlers(vertx);
		log.info("rpc eventbus register successed...");
	}
}
