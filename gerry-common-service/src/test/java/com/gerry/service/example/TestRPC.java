package com.gerry.service.example;

import com.gerry.common.framework.vertx.rpc.utils.VertxRPCUtils;
import com.gerry.common.framework.vertx.utils.VertxCoreUtils;
import com.gerry.service.user.UserService;

public class TestRPC {

	public static void main(String[] args) throws Exception {
		VertxRPCUtils.rpcFactory.registerServer(VertxCoreUtils.vertx.eventBus());
		UserService userService = VertxRPCUtils.rpcFactory.createClient(VertxCoreUtils.vertx.eventBus(), UserService.class);
		userService.save("gerry");
//		userService.delete(1).whenComplete((m,e)->{
//			System.out.println(e.getMessage());
//			System.out.println(m);
//		});
//		userService.add();
	}
}
