package com.gerry.common.app.web.handler.user;

import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import lombok.extern.log4j.Log4j;

import com.gerry.common.app.web.dao.product.TestCrudRepository;
import com.gerry.common.framework.vertx.annotations.RouteHandler;
import com.gerry.common.framework.vertx.annotations.RouteMapping;
import com.gerry.common.framework.vertx.data.aop.AopCglibAutoProxy;
import com.gerry.common.framework.vertx.exception.VertxInvokeException;
import com.gerry.common.framework.vertx.handler.AbstractInvokeHandler;
import com.gerry.common.framework.vertx.http.helper.ViewModelHelper;
import com.gerry.common.framework.vertx.http.result.ViewModelResult;
import com.gerry.common.framework.vertx.utils.NumberUtils;

/**
 * 一个标准的Handler处理实现
 * 
 *
 * @author gerry
 * @version 1.0.1, 2016年6月25日
 * @since com.gerry 1.0.1
 */
@Log4j
@RouteHandler("/user")
public class UserHandler {

	@RouteMapping(method = HttpMethod.GET)
	public AbstractInvokeHandler products() {
		return new AbstractInvokeHandler() {
			@Override
			public ViewModelResult<?> invoke(HttpServerRequest resquest) throws VertxInvokeException {
				return ViewModelHelper.OKViewModelResult("welcome to vert.x");
			}
		};
	}

	@RouteMapping(value = "/:id", method = HttpMethod.GET)
	public AbstractInvokeHandler getProduct() {
		return new AbstractInvokeHandler() {
			@Override
			public ViewModelResult<?> invoke(HttpServerRequest resquest) throws VertxInvokeException {
				return ViewModelHelper.OKViewModelResult(resquest.getParam("id"));
			}
		};
	}

	@RouteMapping(value = "/add", method = HttpMethod.POST)
	public AbstractInvokeHandler add() {
		return new AbstractInvokeHandler() {
			@Override
			public ViewModelResult<?> invoke(HttpServerRequest resquest) throws VertxInvokeException {
				return ViewModelHelper.OKViewModelResult(resquest.getParam("id"));
			}
		};
	}

	TestCrudRepository test = (TestCrudRepository) AopCglibAutoProxy.createProxyInstance(TestCrudRepository.class);

	@RouteMapping(value = "/delete", method = HttpMethod.DELETE)
	public AbstractInvokeHandler delete() {
		return new AbstractInvokeHandler() {
			@Override
			public ViewModelResult<?> invoke(HttpServerRequest resquest) throws VertxInvokeException {
				test.delete(NumberUtils.parseNumber(resquest.getParam("id"), Long.class), rows -> {
					log.info(rows);
				});
				return ViewModelHelper.OKViewModelResult(resquest.getParam("id"));
			}

		};
	}

	@RouteMapping(value = "/update", method = HttpMethod.PUT)
	public AbstractInvokeHandler update() {
		return new AbstractInvokeHandler() {
			@Override
			public ViewModelResult<?> invoke(HttpServerRequest resquest) throws VertxInvokeException {
				return ViewModelHelper.OKViewModelResult(resquest.getParam("id"));
			}
		};
	}
}
