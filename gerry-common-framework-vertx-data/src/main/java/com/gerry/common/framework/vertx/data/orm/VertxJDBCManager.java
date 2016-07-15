package com.gerry.common.framework.vertx.data.orm;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.SQLConnection;
import lombok.extern.log4j.Log4j;

import com.gerry.common.framework.vertx.configuration.VertxConfiguration;
import com.gerry.common.framework.vertx.exception.VertxInvokeException;
import com.gerry.common.framework.vertx.utils.VertxEmptyUtils;

@Log4j
public class VertxJDBCManager {

	public static VertxJDBCManager INSTANCE = new VertxJDBCManager();

	public static JDBCClient jdbcClient = JDBCClient.createShared(Vertx.vertx(), VertxConfiguration.jdbcJsonObject());

	private static SQLConnection connection;

	public static void init() {
		if (VertxEmptyUtils.isEmpty(connection)) {
			long start = System.currentTimeMillis();
			log.info(" connect jdbc start ...");
			jdbcClient.getConnection(res -> {
				if (res.failed()) {
					throw new VertxInvokeException(" vertx connect to db failed");
				}
				connection = res.result();
				log.info(" connect jdbc success， " + (System.currentTimeMillis() - start) + "ms ...");
			});
		}
	}

	public void add(String sql, Handler<Integer> handler) {
		update(sql, handler);
	}

	public void delete(String sql, Handler<Integer> handler) {
		update(sql, handler);
	}

	public void update(String sql, Handler<Integer> handler) {
		log.info(" execute operation , sql : [" + sql + "]");
		connection.update(sql, res -> {
			if (res.succeeded()) {
				handler.handle(res.result().getUpdated());
			} else {
				throw new VertxInvokeException(res.cause());
			}
		});
	}

	public void select(String sql, Handler<ResultSet> handler) {
		log.info(" execute query , sql : [" + sql + "]");
		connection.query(sql, res -> {
			if (res.succeeded()) {
				handler.handle(res.result());
			} else {
				res.cause();
			}
		});
	}

	public void exists(String sql, Handler<Boolean> handler) {
		log.info(" execute query , sql : [" + sql + "]");
		connection.query(sql, res -> {
			if (res.succeeded()) {
				if (res.result().getNumColumns() > 0) {
					handler.handle(true);
				} else {
					handler.handle(false);
				}
			} else {
				res.cause();
			}
		});
	}

	public void execute(String sql, Handler<AsyncResult<Void>> handler) {
		log.info(" execute operation , sql : [" + sql + "]");
		connection.execute(sql, handler);
	}

}
