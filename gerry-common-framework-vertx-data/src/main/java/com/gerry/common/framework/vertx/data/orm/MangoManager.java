package com.gerry.common.framework.vertx.data.orm;

public interface MangoManager {

	static <T> T create(Class<T> clazz) {
		return MangoConfiguration.create(clazz);
	}

}
