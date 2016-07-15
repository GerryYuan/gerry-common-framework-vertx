package com.gerry.common.framework.vertx.data.handler;

import io.vertx.core.AsyncResult;
import io.vertx.ext.sql.ResultSet;
import lombok.extern.log4j.Log4j;

import com.gerry.common.framework.vertx.exception.VertxInvokeException;

@Log4j
public abstract class AbstractResultSetInvokeHandler implements ResultSetInvokeHandler<AsyncResult<ResultSet>> {

	@Override
	public void handle(AsyncResult<ResultSet> res) {
		if (res.failed()) {
            log.error(res.cause().getMessage(), res.cause());
            throw new VertxInvokeException(res.cause());
        }
		
		convertResultSet(res.result());
	}

}
