package com.gerry.common.framework.vertx.data.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;

import com.gerry.common.framework.vertx.data.annotations.Delete;
import com.gerry.common.framework.vertx.data.orm.EntityManager;
import com.gerry.common.framework.vertx.data.utils.AnnotationUtils;

public class BeforeMethodInvokation extends AbstractBeforeMethodInterceptor {

	@Override
	public Object instance(Class<?> clazz) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(clazz);
		// 设置回调函数
		enhancer.setCallback(this);
		return enhancer.create();
	}

	@Override
	public  void invoke(Object obj, Method method, Object[] args) throws Throwable {
		Delete delete = AnnotationUtils.getAnnotation(method, Delete.class);
		//把sql通过EntityManager来管理，放到共享缓存当中，当执行该方法时，从共享缓存种获取当前sql
		EntityManager.convertSql(delete.value(), obj.getClass().getSuperclass());
	}

}
