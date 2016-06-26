package com.gerry.common.framework.handler.vertx;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerRequest;

import com.gerry.common.framework.exception.vertx.VertxInvokeException;
import com.gerry.common.framework.http.result.vertx.ViewModelResult;

public interface InvokeHandler<E> extends Handler<E>{

	ViewModelResult<?> invoke(HttpServerRequest resquest) throws VertxInvokeException;

}
