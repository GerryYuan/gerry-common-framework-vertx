package com.gerry.common.framework.vertx.rpc.impl;

import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;

import java.lang.reflect.Method;
import java.util.Objects;

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
	public <T> T createClient(EventBus eventBus, Class<T> iface) {
		EventBusServiceClient service = ReflectionUtils.getAnnotationInterface(iface, EventBusServiceClient.class).getAnnotation(EventBusServiceClient.class);
		if (service == null) {
			throw new VertxRPCException("Interface should has EventBusService annotiation.");
		}
		String address = VertxEmptyUtils.isEmpty(service.value()) ? iface.getName() : service.value();
		return Reflection.newProxy(iface, (proxy, method, args) -> {
			FastJsonMessage msg = MessageConverterHelper.converter(args);
			returnVoid(method, eventBus, address, msg, EventBusHeaderFactory.defaultHeader(new DeliveryOptions(), method.getName()));
			return null;
		});
	}

	@Override
	public void registerServer(EventBus eventBus) throws VertxRPCException {
		EventBusHandlersFactory.registerHandlers(eventBus);
	}

	private static void returnVoid(Method method, EventBus eventBus, String address, FastJsonMessage msg, DeliveryOptions options) {
		if (getAnnotationValue(method) == ConsumerEnums.PUBLISH) {
			eventBus.publish(address, msg, options);
		} else if (getAnnotationValue(method) == ConsumerEnums.SEND) {
			log.info(String.format("eventbus client send address -> %s", address));
			eventBus.<FastJsonMessage> send(address, msg, options, res -> {
				if (res.failed()) {
					log.error("", res.cause());
					return;
				}
				log.info(res.result().body().getArgs()[0]);
			});
		}
	}

	private static ConsumerEnums getAnnotationValue(Method method) {
		if (!method.isAnnotationPresent(Consumer.class)) {
			Objects.requireNonNull(null, "send eventbus method must comsumer annotation");
		}
		return method.getAnnotation(Consumer.class).type();
	}

}
