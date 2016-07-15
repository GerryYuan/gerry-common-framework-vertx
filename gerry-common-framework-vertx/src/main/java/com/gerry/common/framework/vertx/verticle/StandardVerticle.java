package com.gerry.common.framework.vertx.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CookieHandler;
import io.vertx.ext.web.handler.StaticHandler;
import lombok.extern.log4j.Log4j;

import com.gerry.common.framework.vertx.configuration.VertxConfiguration;
import com.gerry.common.framework.vertx.handler.HandlersFactory;
import com.gerry.common.framework.vertx.utils.FileUtils;

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

	private String uploadUrl = VertxConfiguration.configStr("upload.files.url");

	@Override
	public void start() throws Exception {
		Router router = Router.router(vertx);
		/*router.route("/").handler(
				routingContext -> {
					routingContext
							.response()
							.putHeader("content-type", "text/html")
							.end("<form action=\"/pro/upload\" method=\"post\" enctype=\"multipart/form-data\">\n" + "    <div>\n" + "        <label for=\"name\">Select a file:</label>\n"
									+ "        <input type=\"file\" name=\"file\" />\n" + "    </div>\n" + "    <div class=\"button\">\n" + "        <button type=\"submit\">Send</button>\n"
									+ "    </div>" + "</form>");
				});*/
        router.route().handler(CookieHandler.create());
		router.route().handler(BodyHandler.create().setUploadsDirectory(FileUtils.generationFileDirectory(uploadUrl)));
		// 初始化所有路由，以及处理handler
//		router.route().handler(SessionHandler.create(LocalSessionStore.create(vertx)));
		HandlersFactory.registerHandlers(router);
		// Must be the latest handler to register
		router.route().handler(StaticHandler.create());
		vertx.createHttpServer().requestHandler(router::accept).listen(port);
		log.info("Start server at port " + port + " .....");
	}

}