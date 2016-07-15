package com.gerry.common.framework.vertx.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.DeliveryOptions;
import lombok.extern.log4j.Log4j;

import com.gerry.common.framework.eventbus.handler.EventBusHandlersFactory;
import com.gerry.common.framework.eventbus.message.FastJsonMessage;
import com.gerry.common.framework.eventbus.message.helper.MessageConverterHelper;
import com.gerry.common.framework.vertx.fastjson.VertxFastJsonMessageConverter;
import com.gerry.common.framework.vertx.utils.VertxCoreUtils;

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
		EventBusHandlersFactory.registerHandlers(VertxCoreUtils.vertx.eventBus());
		log.info("eventbus register successed...");

		DeliveryOptions options = new DeliveryOptions().addHeader("method", "delete");
		VertxCoreUtils.vertx.eventBus().<FastJsonMessage> send("com.gerry.service.user.UserService", MessageConverterHelper.converter(new Integer[]{1}), options, res -> {
			if (res.failed()) {
				log.error(res.cause());
				return;
			}
			FastJsonMessage msg = res.result().body();
			log.info(VertxFastJsonMessageConverter.converter(msg));
		});
	}
}
