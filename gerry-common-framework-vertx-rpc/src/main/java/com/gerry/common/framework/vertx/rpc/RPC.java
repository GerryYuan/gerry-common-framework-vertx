package com.gerry.common.framework.vertx.rpc;

import io.vertx.core.ServiceHelper;

public interface RPC {
	
	static RPCFactory newRPC() {
		return (RPCFactory) factory.newFactory();
	}
	
	static final RPCFactory factory = ServiceHelper.loadFactory(RPCFactory.class);

}
