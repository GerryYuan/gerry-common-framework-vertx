package com.gerry.common.framework.vertx.rpc.impl;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import lombok.extern.log4j.Log4j;

import com.gerry.common.framework.eventbus.annotations.Consumer;
import com.gerry.common.framework.eventbus.annotations.EventBusServiceClient;
import com.gerry.common.framework.eventbus.enums.ConsumerEnums;
import com.gerry.common.framework.eventbus.handler.EventBusHandlersFactory;
import com.gerry.common.framework.eventbus.message.FastJsonMessage;
import com.gerry.common.framework.eventbus.message.helper.MessageConverterHelper;
import com.gerry.common.framework.vertx.core.EventBusHeaderFactory;
import com.gerry.common.framework.vertx.rpc.RPCFactory;
import com.gerry.common.framework.vertx.rpc.exception.VertxRPCException;
import com.gerry.common.framework.vertx.utils.ReflectionUtils;
import com.gerry.common.framework.vertx.utils.VertxEmptyUtils;
import com.google.common.reflect.Reflection;

@Log4j
public class EventBusRPCFactory implements RPCFactory {

	private static RPCFactory RPCFactory;

	@Override
	public RPCFactory newFactory() {
		if (!VertxEmptyUtils.isEmpty(RPCFactory)) {
			return RPCFactory;
		}
		return new EventBusRPCFactory();
	}

	@Override
	public <T> T createClient(Vertx vertx, Class<T> iface) {
		EventBusServiceClient service = ReflectionUtils.getAnnotationInterface(iface, EventBusServiceClient.class).getAnnotation(EventBusServiceClient.class);
		if (service == null) {
			throw new VertxRPCException("Interface should has EventBusService annotiation.");
		}
		String address = VertxEmptyUtils.isEmpty(service.value()) ? iface.getName() : service.value();
		return Reflection.newProxy(iface, (proxy, method, args) -> {
			FastJsonMessage msg = MessageConverterHelper.converter(args);
			return invokeSend(method, vertx, address, msg, EventBusHeaderFactory.defaultHeader(new DeliveryOptions(), method.getName()));
		});
	}

	@Override
	public void registerServer(Vertx vertx) throws VertxRPCException {
		EventBusHandlersFactory.registerHandlers(vertx);
	}

	private static Object invokeSend(Method method, Vertx vertx, String address, FastJsonMessage msg, DeliveryOptions options) throws InterruptedException, ExecutionException {
		if (method.getReturnType() == void.class) {
			sendVoid(method, vertx, address, msg, options);
			return null;
		} else {
			return sendReturn(method, vertx, address, msg, options);
		}
	}

	private static void sendVoid(Method method, Vertx vertx, String address, FastJsonMessage msg, DeliveryOptions options) {
		log.info(String.format("eventbus client send or publish address -> %s，no return ", address));
		if (getAnnotationValue(method) == ConsumerEnums.PUBLISH) {
			vertx.eventBus().publish(address, msg, options);
		} else if (getAnnotationValue(method) == ConsumerEnums.SEND) {
			vertx.eventBus().send(address, msg, options);
		}
	}

	private static Object sendReturn(Method method, Vertx vertx, String address, FastJsonMessage msg, DeliveryOptions options) throws InterruptedException, ExecutionException {
		/*
		 * if
		 * (!method.getReturnType().isAssignableFrom(CompletableFuture.class)) {
		 * throw new VertxRPCException(
		 * "EventBusService support only CompletableFuture returns"); }
		 */
		CompletableFuture<Object> result = new CompletableFuture<>();
		log.info(String.format("eventbus client send or publish address -> %s ", address));
		vertx.eventBus().<FastJsonMessage> send(address, msg, options, res -> {
			if (res.failed()) {
				result.completeExceptionally(res.cause());
				log.error("", res.cause());
				return;
			}
			result.complete(res.result().body().getArgs()[0]);
			/*
			 * vertx.executeBlocking(future -> {
			 * future.complete(res.result().body().getArgs()[0]); }, blockResult
			 * -> { result.complete(blockResult); });
			 */
			try {
				log.info(" accept eventbus server ack " + result.get());
			} catch (Exception e) {
				log.error("", e);
			}
		});
		return result;
	}

	private static ConsumerEnums getAnnotationValue(Method method) {
		if (!method.isAnnotationPresent(Consumer.class)) {
			Objects.requireNonNull(null, "send eventbus method must comsumer annotation");
		}
		return method.getAnnotation(Consumer.class).type();
	}

}
