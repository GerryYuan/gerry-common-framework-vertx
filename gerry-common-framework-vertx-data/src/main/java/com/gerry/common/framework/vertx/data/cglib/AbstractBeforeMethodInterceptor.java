package com.gerry.common.framework.vertx.data.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodProxy;

public abstract class AbstractBeforeMethodInterceptor implements BeforeMethodInterceptor {

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		invoke(obj, method, args);
		return proxy.invokeSuper(obj, args);
	}

}
