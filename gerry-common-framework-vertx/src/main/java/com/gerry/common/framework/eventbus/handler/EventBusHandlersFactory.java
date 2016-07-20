package com.gerry.common.framework.eventbus.handler;

import io.vertx.core.Vertx;

import java.lang.reflect.Method;
import java.util.Set;

import lombok.extern.log4j.Log4j;

import org.reflections.Reflections;

import com.gerry.common.framework.eventbus.annotations.EventBusServiceClient;
import com.gerry.common.framework.eventbus.annotations.EventBusServiceProvider;
import com.gerry.common.framework.eventbus.codecs.FastJsonMessageCodec;
import com.gerry.common.framework.eventbus.message.FastJsonMessage;
import com.gerry.common.framework.eventbus.message.helper.MessageConverterHelper;
import com.gerry.common.framework.vertx.configuration.VertxConfiguration;
import com.gerry.common.framework.vertx.core.EventBusHeaderFactory;
import com.gerry.common.framework.vertx.utils.ReflectionUtils;

/**
 * 注册eventbus consumer
 * 
 *
 * @author gerry
 * @version 1.0, 2016年7月8日下午12:34:16
 * @since com.gerry.link 1.0
 */
@Log4j
public class EventBusHandlersFactory {

	private static final Reflections reflections = new Reflections(VertxConfiguration.configStr("scan.service.url"));

	public static void registerHandlers(Vertx vertx) {
		log.info("Register eventbus default codec " + FastJsonMessageCodec.class.getName());
		vertx.eventBus().registerDefaultCodec(FastJsonMessage.class, new FastJsonMessageCodec());
		Set<Class<?>> handlers = reflections.getTypesAnnotatedWith(EventBusServiceProvider.class);
		for (Class<?> handlerClass : handlers) {
			try {
				if (handlerClass.isAnnotationPresent(EventBusServiceProvider.class)) {
					invokeHandlers(vertx, handlerClass);
				}
			} catch (Exception e) {
				log.error("Error register consumer " + handlerClass, e);
			}
		}
	}

	private static void invokeHandlers(Vertx vertx, Class<?> handlerClass) throws Exception {
		Object serviceHandler = handlerClass.newInstance();
		Class<?> clazz = ReflectionUtils.getAnnotationInterface(handlerClass, EventBusServiceClient.class);
		String address = clazz.getName();// 注解接口的类的名称
		for (Method method : handlerClass.getDeclaredMethods()) {
			EventBusHeaderFactory.storage(address).put(method.getName(), method);
		}

		log.info("Register eventbus consumer address -> " + address);
		vertx.eventBus().<FastJsonMessage> consumer(address, res -> {
			String method = EventBusHeaderFactory.defaultHeader(res);
			if (!EventBusHeaderFactory.storage(address).containsKey(method)) {
				String error = String.format("Method %s not found", method);
				log.error(error);
				res.fail(1, error);
				return;
			}

			Method md = EventBusHeaderFactory.storage(address).get(method);
			Object[] params = res.body().getArgs();
			try {
				vertx.executeBlocking(future -> {
					try {
						Object result = md.invoke(serviceHandler, params);
						future.complete(result);
					} catch (Exception e) {
						log.error("", e);
					}
				}, result -> {
					if (result.succeeded()) {
						res.reply(MessageConverterHelper.converter(new Object[] { result.result() }));
					} else {
						log.error("", result.cause());
						res.reply(MessageConverterHelper.converter(new Object[] { result.cause() }));
					}
				});
			} catch (Exception e) {
				log.error("", e);
				res.fail(1, e.getMessage());
			}
		});
	}

}
