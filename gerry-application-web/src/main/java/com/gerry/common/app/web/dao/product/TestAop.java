package com.gerry.common.app.web.dao.product;

import com.gerry.common.framework.vertx.data.aop.AopCglibAutoProxy;
import com.gerry.common.framework.vertx.data.handler.AbstractUpdateResultInvokeHandler;

public class TestAop {
	public static void main(String[] args) {
		TestCrudRepository test = (TestCrudRepository)AopCglibAutoProxy.createProxyInstance(TestCrudRepository.class);
		test.delete(1l, new AbstractUpdateResultInvokeHandler() {
			@Override
			public void result(int rows) {
				System.out.println(rows);
			}
		});
	}
}
