package com.gerry.common.framework.vertx.data.aop;

import java.util.Set;
import java.util.concurrent.ConcurrentMap;

import org.reflections.Reflections;

import com.gerry.common.framework.vertx.data.annotations.Repository;
import com.gerry.common.framework.vertx.data.cglib.BeforeMethodInvokation;
import com.google.common.collect.Maps;

public class AopCglibAutoProxy {

//	private static final Reflections reflections = new Reflections("com.gerry.common.framework.data.repository.support.vertx");
	
	private static final Reflections reflections = new Reflections("com.gerry.common.app.web.dao.product");
	
	private static ConcurrentMap<Class<?>, Object> classMaps = Maps.newConcurrentMap();

	// 扫描带有注解Repository的实体类，通过注解的方式进行封装jdbc层
	static Set<Class<?>> handlers = reflections.getTypesAnnotatedWith(Repository.class);
	static {
		for (Class<?> clazz : handlers) {
			classMaps.put(clazz, new BeforeMethodInvokation().instance(clazz));
		}
	}

	public static Object createProxyInstance(Class<?> clazz) {
		return classMaps.get(clazz);
	}

}
