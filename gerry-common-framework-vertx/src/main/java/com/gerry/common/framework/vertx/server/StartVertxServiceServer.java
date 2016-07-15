package com.gerry.common.framework.vertx.server;

import io.vertx.core.Vertx;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import lombok.extern.log4j.Log4j;

import org.apache.log4j.PropertyConfigurator;

import com.gerry.common.framework.vertx.verticle.StandardServiceVerticle;

@Log4j
public class StartVertxServiceServer {

	public static void main(String[] args) throws FileNotFoundException {
		PropertyConfigurator.configure(new FileInputStream(new File("src/main/resources/log4j.properties")));
		log.info("Vertx log4j initialized");
		Vertx.vertx().deployVerticle(new StandardServiceVerticle(), res -> {
			if (res.succeeded())
				log.info("Verticle deployed ok, deploymentId = " + res.result());
			else
				log.error("Verticle deployed failed", res.cause());
		});
	}
}
