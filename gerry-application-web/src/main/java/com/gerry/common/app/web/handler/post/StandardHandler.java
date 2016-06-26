package com.gerry.common.app.web.handler.post;

import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;

import com.gerry.common.framework.annotations.vertx.RouteHandler;
import com.gerry.common.framework.annotations.vertx.RouteMapping;
import com.gerry.common.framework.exception.vertx.VertxInvokeException;
import com.gerry.common.framework.handler.vertx.AbstractInvokeHandler;
import com.gerry.common.framework.http.helper.vertx.ViewModelHelper;
import com.gerry.common.framework.http.result.vertx.ViewModelResult;


/**
 * 一个标准的Handler处理实现
 * 
 *
 * @author gerry
 * @version 1.0.1, 2016年6月25日
 * @since com.gerry 1.0.1
 */
@RouteHandler("/pro")
public class StandardHandler {

	@RouteMapping(method = HttpMethod.GET)
	public AbstractInvokeHandler products() {
		return new AbstractInvokeHandler() {
			@Override
			public ViewModelResult<?> invoke(HttpServerRequest resquest) throws VertxInvokeException {
				return ViewModelHelper.OKViewModelResult("welcome to vert.x");
			}
		};
	}

	@RouteMapping(value="/:id", method = HttpMethod.GET)
	public AbstractInvokeHandler getProduct() {
		return new AbstractInvokeHandler() {
			@Override
			public ViewModelResult<?> invoke(HttpServerRequest resquest) throws VertxInvokeException {
				return ViewModelHelper.OKViewModelResult(resquest.getParam("id"));
			}
		};
	}
	
}
