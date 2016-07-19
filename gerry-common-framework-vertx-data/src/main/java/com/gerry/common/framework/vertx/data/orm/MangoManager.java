package com.gerry.common.framework.vertx.data.orm;

/**
 * mango操作管理接口
 * 
 *
 * @author gerry
 * @version 1.0, 2016年7月18日下午6:57:20
 * @since com.gerry.link 1.0
 */
public interface MangoManager {

	static void init() {
		MangoConfiguration.init();
	}

	static <T> T create(Class<T> clazz) {
		return MangoConfiguration.create(clazz);
	}

}
