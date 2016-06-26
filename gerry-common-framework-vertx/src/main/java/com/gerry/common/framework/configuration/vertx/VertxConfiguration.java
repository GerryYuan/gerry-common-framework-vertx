package com.gerry.common.framework.configuration.vertx;

import io.vertx.core.json.JsonObject;

import java.net.URL;
import java.util.Map;

import lombok.extern.log4j.Log4j;

import com.fasterxml.jackson.databind.ObjectMapper;

@Log4j
public class VertxConfiguration {

	private static final String CONFIG_NAME = "config.json";

	public static final VertxConfiguration configuration = new VertxConfiguration();

    private static JsonObject config;

	@SuppressWarnings("unchecked")
	private VertxConfiguration() {
		try {
			URL url = getClass().getClassLoader().getResource(CONFIG_NAME);
			log.info("Initialize configuration from path : " + url);
			ObjectMapper mapper = new ObjectMapper();
			config = new JsonObject((Map<String, Object>) mapper.readValue(url, Map.class));
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}
	}
	
	public static String configStr(String key) {
        return config.getString(key);
    }

    public static Integer configInt(String key) {
        return config.getInteger(key);
    }
}
