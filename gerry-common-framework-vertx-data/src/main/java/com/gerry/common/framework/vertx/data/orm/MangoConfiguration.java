package com.gerry.common.framework.vertx.data.orm;

import io.vertx.core.json.JsonObject;

import javax.sql.DataSource;

import org.jfaster.mango.datasource.DriverManagerDataSource;
import org.jfaster.mango.operator.Mango;

import com.gerry.common.framework.vertx.configuration.VertxConfiguration;
import com.gerry.common.framework.vertx.utils.VertxEmptyUtils;

/**
 * mango的配置
 * 
 *
 * @author gerry
 * @version 1.0, 2016年7月18日下午2:00:27
 * @since com.gerry.link 1.0
 */
public class MangoConfiguration {

	public static Mango mango;

	static void init() {
		if (VertxEmptyUtils.isNotEmpty(mango)) {
			return;
		}
		JsonObject json = VertxConfiguration.jdbcJsonObject();
		DataSource ds = new DriverManagerDataSource(json.getString("driver_class"), json.getString("url"), json.getString("user"), json.getString("password"));
		mango = Mango.newInstance(ds);
	}
}
