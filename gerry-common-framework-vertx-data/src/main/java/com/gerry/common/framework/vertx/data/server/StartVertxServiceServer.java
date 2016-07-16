package com.gerry.common.framework.vertx.data.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.log4j.PropertyConfigurator;

public class StartVertxServiceServer {

	public static void main(String[] args) throws FileNotFoundException {
		PropertyConfigurator.configure(new FileInputStream(new File("src/main/resources/log4j.properties")));
		/*RedisOptions options = new RedisOptions(json);
		RedisClient.create(VertxCoreUtils.vertx, options);*/
	}
}
