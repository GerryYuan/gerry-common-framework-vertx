package com.gerry.common.framework.vertx.data.handler;

import io.vertx.core.Handler;
import io.vertx.ext.sql.ResultSet;

public interface ResultSetInvokeHandler<E> extends Handler<E> {

	void convertResultSet(ResultSet resultSet);
	
}
