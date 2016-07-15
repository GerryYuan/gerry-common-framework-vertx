package com.gerry.common.framework.vertx.data.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;

public interface BeforeMethodInterceptor extends MethodInterceptor {

	void invoke(Object obj, Method method, Object[] args) throws Throwable;

	Object instance(Class<?> clazz);

}
