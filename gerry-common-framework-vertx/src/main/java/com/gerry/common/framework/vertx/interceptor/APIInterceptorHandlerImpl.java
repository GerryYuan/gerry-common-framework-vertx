package com.gerry.common.framework.vertx.interceptor;

import io.vertx.ext.auth.AuthProvider;
import io.vertx.ext.auth.User;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.Session;
import io.vertx.ext.web.handler.impl.AuthHandlerImpl;

import com.gerry.common.framework.vertx.utils.VertxEmptyUtils;

/**
 * api拦截（例子，可参照）
 * 
 *
 * @author gerry
 * @version  1.0, 2016年7月7日上午11:58:06
 * @since   com.gerry.link 1.0
 */
public class APIInterceptorHandlerImpl extends AuthHandlerImpl {

	public APIInterceptorHandlerImpl(AuthProvider authProvider) {
		super(authProvider);
	}

	@Override
	public void handle(RoutingContext context) {
		Session session = context.session();
		if (VertxEmptyUtils.isEmpty(session)) {
			context.fail(new NullPointerException("No session.."));
		} else {
			User user = context.user();
			if (VertxEmptyUtils.isNotEmpty(session)) {
                authorise(user, context);
            } else {
                context.response().setStatusCode(401).end(); // Unauthorized
            }
		}
	}

}
