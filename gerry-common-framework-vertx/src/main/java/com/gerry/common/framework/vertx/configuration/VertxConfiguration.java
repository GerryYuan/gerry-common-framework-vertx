package com.gerry.common.framework.vertx.configuration;

import io.vertx.core.json.JsonObject;

import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import lombok.extern.log4j.Log4j;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gerry.common.framework.vertx.utils.VertxEmptyUtils;
import com.google.common.collect.Maps;

@Log4j
public class VertxConfiguration {

	private static final String[] CONFIG_NAMES = { "config.json", "jdbc.json" };

	static final VertxConfiguration configuration = new VertxConfiguration();

	private static ConcurrentMap<String, JsonObject> configMaps;

	@SuppressWarnings("unchecked")
	private VertxConfiguration() {
		try {
			for (String config : CONFIG_NAMES) {
				URL url = getClass().getClassLoader().getResource(config);
				if (VertxEmptyUtils.isEmpty(url)) {
					log.info("file " + config + " is not exist, continue.....");
					continue;
				}
				log.info("Initialize configuration from path : " + url);
				ObjectMapper mapper = new ObjectMapper();
				if (VertxEmptyUtils.isEmpty(configMaps)) {
					configMaps = Maps.newConcurrentMap();
				}
				configMaps.put(config, new JsonObject((Map<String, Object>) mapper.readValue(url, Map.class)));
			}
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}
	}

	public static String configStr(String configStr) {
		return configMaps.get("config.json").getString(configStr);
	}

	public static String jdbcStr(String configStr) {
		return configMaps.get("jdbc.json").getString(configStr);
	}

	public static JsonObject jdbcJsonObject() {
		JsonObject config = new JsonObject().put("url", jdbcStr("jdbc.url")).put("driver_class", jdbcStr("jdbc.driver_class"));
		String username = jdbcStr("jdbc.username");
		if (VertxEmptyUtils.isNotEmpty(username))
			config.put("user", username);

		String password = jdbcStr("jdbc.password");
		if (VertxEmptyUtils.isNotEmpty(password))
			config.put("password", password);

		return config;
	}

	public static Integer configInt(String configStr) {
		return configMaps.get("config.json").getInteger(configStr);
	}

	public static Integer jdbcInt(String configStr) {
		return configMaps.get("jdbc.json").getInteger(configStr);
	}

}
