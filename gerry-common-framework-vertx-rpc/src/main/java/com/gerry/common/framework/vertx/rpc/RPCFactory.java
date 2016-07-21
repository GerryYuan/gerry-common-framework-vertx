package com.gerry.common.framework.vertx.rpc;

import io.vertx.core.Vertx;

import com.gerry.common.framework.vertx.core.Factory;
import com.gerry.common.framework.vertx.rpc.exception.VertxRPCException;

/**
 * rpc工厂
 * 
 *
 * @author gerry
 * @version 1.0, 2016年7月11日下午4:16:21
 * @since com.gerry.link 1.0
 */
public interface RPCFactory extends Factory {

	/**
	 * 创建客户端
	 * 
	 * @param vertx
	 * @param iface
	 * @return
	 * @see
	 */
	<T> T createClient(Vertx vertx, Class<T> iface);

	/**
	 * 注册服务端
	 * 
	 * @param vertx
	 * @throws VertxRPCException
	 * @see
	 */
	void registerServer(Vertx vertx) throws VertxRPCException;

}
