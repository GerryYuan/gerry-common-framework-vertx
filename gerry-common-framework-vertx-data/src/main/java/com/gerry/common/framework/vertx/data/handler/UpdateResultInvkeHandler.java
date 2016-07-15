package com.gerry.common.framework.vertx.data.handler;

import io.vertx.core.Handler;

public interface UpdateResultInvkeHandler<E> extends Handler<E> {
	void result(int rows);
}
