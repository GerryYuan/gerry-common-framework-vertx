package com.gerry.common.framework.vertx.data.handler;


public abstract class AbstractUpdateResultInvokeHandler implements UpdateResultInvkeHandler<Integer> {

	@Override
	public void handle(Integer rows) {
		result(rows);
	}
}
