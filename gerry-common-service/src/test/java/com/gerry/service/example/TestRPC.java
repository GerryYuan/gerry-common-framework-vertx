package com.gerry.service.example;

import org.jfaster.mango.operator.Mango;

import com.gerry.common.framework.vertx.data.orm.MangoManager;
import com.gerry.service.dao.entity.Fruit;
import com.gerry.service.dao.fruit.FruitDao;


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
		
		Mango mango = MangoManager.getInstance();
		FruitDao fruitDao = mango.create(FruitDao.class);
		Fruit fruit = new Fruit();
		fruit.setName("name1");
		fruit.setNum(1);
		fruitDao.addFruit(fruit);
	}
}
