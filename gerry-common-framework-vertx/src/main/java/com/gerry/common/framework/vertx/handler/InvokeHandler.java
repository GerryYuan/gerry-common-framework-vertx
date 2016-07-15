package com.gerry.common.framework.vertx.handler;

import io.vertx.core.http.HttpServerRequest;

import com.gerry.common.framework.vertx.exception.VertxInvokeException;
import com.gerry.common.framework.vertx.http.result.ViewModelResult;

public interface InvokeHandler<E> extends StandardInvokeHandler<E>{

	ViewModelResult<?> invoke(HttpServerRequest resquest) throws VertxInvokeException;

}
