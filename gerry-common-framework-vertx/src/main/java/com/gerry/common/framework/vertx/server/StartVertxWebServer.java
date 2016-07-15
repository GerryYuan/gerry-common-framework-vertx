package com.gerry.common.framework.vertx.server;

import io.vertx.core.Vertx;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import lombok.extern.log4j.Log4j;

import org.apache.log4j.PropertyConfigurator;

import com.gerry.common.framework.vertx.verticle.StandardVerticle;

/**
 * vertx web服务启动类
 * 
 *
 * @author gerry
 * @version 1.0, 2016年6月21日下午12:30:48
 * @since com.gerry.link 1.0
 */
@Log4j
public class StartVertxWebServer {

	public static void main(String[] args) throws IOException {
		PropertyConfigurator.configure(new FileInputStream(new File("src/main/resources/log4j.properties")));
		log.info("Vertx log4j initialized");
		// Deploy another instance and want for it to start
		Vertx.vertx().deployVerticle(new StandardVerticle(), res -> {
			if (res.succeeded()) {
				String deploymentId = res.result();
				log.info("Verticle deployed ok, deploymentId = " + deploymentId);
			} else {
				log.error("Verticle deployed failed", res.cause());
			}
		});
	}
}
