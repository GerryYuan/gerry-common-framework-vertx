package com.gerry.common.framework.verticle.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;
import lombok.extern.log4j.Log4j;

import com.gerry.common.framework.configuration.vertx.VertxConfiguration;
import com.gerry.common.framework.handler.vertx.HandlersFactory;

/**
 * vertx web server
 * 
 *
 * @author gerry
 * @version 1.0, 2016年6月21日下午12:23:51
 * @since com.gerry.link 1.0
 */
@Log4j
public class StandardVerticle extends AbstractVerticle {

	private Integer port = VertxConfiguration.configInt("port");

	@Override
	public void start() throws Exception {
		Router router = Router.router(vertx);
		// 初始化所有路由，以及处理handler
//		router.route().handler(SessionHandler.create(LocalSessionStore.create(vertx)));
		HandlersFactory.registerHandlers(router);
		// Must be the latest handler to register
		router.route().handler(StaticHandler.create());
		vertx.createHttpServer().requestHandler(router::accept).listen(port);
		log.info("Start server at port " + port + " .....");
	}

}