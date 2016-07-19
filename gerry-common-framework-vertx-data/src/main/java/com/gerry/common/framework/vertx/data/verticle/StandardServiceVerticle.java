package com.gerry.common.framework.vertx.data.verticle;

import io.vertx.core.AbstractVerticle;
import lombok.extern.log4j.Log4j;

import com.gerry.common.framework.eventbus.handler.EventBusHandlersFactory;
import com.gerry.common.framework.vertx.data.orm.MangoManager;

/**
 * 一个标准的serviceVerticle
 * 
 *
 * @author gerry
 * @version 1.0, 2016年7月8日上午11:20:27
 * @since com.gerry.link 1.0
 */
@Log4j
public class StandardServiceVerticle extends AbstractVerticle {

	@Override
	public void start() throws Exception {
		// 接受，启动时需要注册
		EventBusHandlersFactory.registerHandlers(vertx.eventBus());
		log.info("eventbus register successed...");
		MangoManager.init();
		log.info("mango jdbc connected...");
	}
}
