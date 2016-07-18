package com.gerry.service.example;

import com.gerry.common.framework.vertx.data.orm.MangoManager;
import com.gerry.dao.fruit.FruitDao;
import com.gerry.service.dao.entity.Fruit;


public class TestRPC {

	public static void main(String[] args) throws Exception {
		/*VertxRPCUtils.rpcFactory.registerServer(VertxCoreUtils.vertx.eventBus());
		UserService userService = VertxRPCUtils.rpcFactory.createClient(VertxCoreUtils.vertx.eventBus(), UserService.class);
		userService.save("gerry");*/
//		userService.delete(1).whenComplete((m,e)->{
//			System.out.println(e.getMessage());
//			System.out.println(m);
//		});
//		userService.add();
		
		FruitDao fruitDao =  MangoManager.create(FruitDao.class);
		Fruit fruit = new Fruit();
		fruit.setName("不错哦1111");
		fruit.setNum(1);
		fruitDao.addFruit(fruit);
	}
}
