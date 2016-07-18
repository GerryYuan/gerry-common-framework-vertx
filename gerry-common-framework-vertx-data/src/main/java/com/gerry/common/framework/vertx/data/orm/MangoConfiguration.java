package com.gerry.common.framework.vertx.data.orm;

import io.vertx.core.json.JsonObject;

import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import lombok.extern.log4j.Log4j;

import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.datasource.DriverManagerDataSource;
import org.jfaster.mango.operator.Mango;
import org.reflections.Reflections;

import com.gerry.common.framework.vertx.configuration.VertxConfiguration;
import com.gerry.common.framework.vertx.utils.VertxEmptyUtils;
import com.google.common.collect.Maps;

/**
 * mango的配置
 * 
 *
 * @author gerry
 * @version 1.0, 2016年7月18日下午2:00:27
 * @since com.gerry.link 1.0
 */
@Log4j
final public class MangoConfiguration {

	private static final Reflections reflections = new Reflections(VertxConfiguration.configStr("scan.dao.url"));

	private static Map<Class<?>, Object> DAOMAPS = Maps.newConcurrentMap();

	private static Mango mango;

	private static void init() {
		if (VertxEmptyUtils.isNotEmpty(mango)) {
			return;
		}
		JsonObject json = VertxConfiguration.jdbcJsonObject();
		DataSource ds = new DriverManagerDataSource(json.getString("driver_class"), json.getString("url"), json.getString("user"), json.getString("password"));
		mango = Mango.newInstance(ds);

		Set<Class<?>> daos = reflections.getTypesAnnotatedWith(DB.class);
		for (Class<?> dao : daos) {
			try {
				if (dao.isAnnotationPresent(DB.class)) {
					DAOMAPS.put(dao, mango.create(dao));
				}
			} catch (Exception e) {
				log.error("Error scanner dao " + dao, e);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T create(Class<T> clazz) {
		init();
		Object daoImpl = DAOMAPS.get(clazz);
		if (VertxEmptyUtils.isEmpty(daoImpl)) {
			daoImpl = mango.create(clazz);
			DAOMAPS.put(clazz, daoImpl);
		}
		return (T) daoImpl;
	}

}
