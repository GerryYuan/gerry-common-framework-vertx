package com.gerry.common.framework.vertx.data.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Set;

import org.reflections.Reflections;

import com.gerry.common.framework.vertx.data.annotations.Repository;
import com.gerry.common.framework.vertx.utils.VertxEmptyUtils;

public abstract class AnnotationUtils {

	private static final Reflections reflections = new Reflections("com.gerry.common.framework.vertx.data.repository");

	static Set<Class<?>> repositorys = reflections.getTypesAnnotatedWith(Repository.class);

	/**
	 * 获取当前方法上的注解，包括接口方法注解
	 *  
	 * @param method
	 * @param annotationType
	 * @return
	 * @see
	 */
	public static <A extends Annotation> A getAnnotation(Method method, Class<A> annotationType) {
		Class<?> clazz = method.getDeclaringClass();
		Objects.requireNonNull(clazz, "method class must not null");
		Class<?>[] interfaces = clazz.getInterfaces();
		for (Class<?> interfaceClazz : interfaces) {
			for (Class<?> repository : repositorys) {
				if (!repository.getName().equals(interfaceClazz.getName())) {
					continue;
				}
				return getAnnotationWithMethods(method, annotationType, repository.getMethods());
			}
		}
		return null;
	}

	//做缓存，不然性能很差
	private static <A extends Annotation> A getAnnotationWithMethods(Method method, Class<A> annotationType, Method... methods) {
		for (Method me : methods) {
			if(!method.getName().equals(me.getName())){
				continue;
			}
			A annotation = me.getAnnotation(annotationType);
			if (VertxEmptyUtils.isEmpty(annotation)) {
				continue;
			}
			return annotation;
		}
		return null;
	}
}
