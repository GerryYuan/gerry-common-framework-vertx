package com.gerry.common.framework.handler.vertx;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import java.lang.reflect.Method;
import java.util.Set;

import lombok.extern.log4j.Log4j;

import org.reflections.Reflections;

import com.gerry.common.framework.annotations.vertx.RouteHandler;
import com.gerry.common.framework.annotations.vertx.RouteMapping;
import com.gerry.common.framework.configuration.vertx.VertxConfiguration;

@Log4j
public class HandlersFactory {

	private static final Reflections reflections = new Reflections(VertxConfiguration.configStr("scan.route.url"));

	/**
	 * 注册Handlers
	 * 
	 * @see
	 */
	public static void registerHandlers(Router router) {
		log.info("Register available request handlers...");

		Set<Class<?>> handlers = reflections.getTypesAnnotatedWith(RouteHandler.class);
		for (Class<?> handlerClass : handlers) {
			try {
				invokeHandlers(handlerClass, router);
			} catch (Exception e) {
				log.error("Error register " + handlerClass);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private static void invokeHandlers(Class<?> handlerClass, Router router) throws Exception {
		String root = "";
		if (handlerClass.isAnnotationPresent(RouteHandler.class)) {
			RouteHandler routeHandler = handlerClass.getAnnotation(RouteHandler.class);
			root = routeHandler.value();
		}

		Object handler = handlerClass.newInstance();
		for (Method method : handlerClass.getMethods()) {
			if (method.isAnnotationPresent(RouteMapping.class)) {
				RouteMapping mapping = method.getAnnotation(RouteMapping.class);
				HttpMethod routeMethod = mapping.method();
				String url = root + mapping.value();
				log.info("Register Handler -> " + routeMethod + ":" + url + "");
				Handler<RoutingContext> methodHandler = (Handler<RoutingContext>) method.invoke(handler);
				switch (routeMethod) {
				case POST:
					router.post(url).handler(methodHandler);
					break;
				case PUT:
					router.put(url).handler(methodHandler);
					break;
				case DELETE:
					router.delete(url).handler(methodHandler);
					break;
				case GET: // fall through
				default:
					router.get(url).handler(methodHandler);
					break;
				}
			}
		}
	}

}
