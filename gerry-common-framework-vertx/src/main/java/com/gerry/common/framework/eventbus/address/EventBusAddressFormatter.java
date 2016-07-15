package com.gerry.common.framework.eventbus.address;

import java.util.Objects;

import com.gerry.common.framework.eventbus.annotations.Consumer;

public interface EventBusAddressFormatter {

	public static String formate(Consumer address, Class<?> clazz) {
		Objects.requireNonNull(clazz, "clazz must not be null");
		return clazz.getName() + ".";
	}
}
