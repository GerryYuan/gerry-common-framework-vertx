package com.gerry.common.framework.vertx.data.factory;

import io.vertx.core.Vertx;

import com.gerry.common.framework.vertx.core.Factory;

/**
 * 数据工厂接口
 * 
 *
 * @author gerry
 * @version 1.0, 2016年7月16日下午3:41:45
 * @since com.gerry.link 1.0
 */
public interface DataFactory<T> extends Factory {

	/**
	 * 创建客户端
	 * 
	 * @param vertx
	 * @return
	 * @see
	 */
	T createClient(Vertx vertx);

}
