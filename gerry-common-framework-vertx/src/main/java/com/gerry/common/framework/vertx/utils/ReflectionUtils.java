package com.gerry.common.framework.vertx.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;

import com.gerry.common.framework.vertx.exception.VertxInvokeException;

public class ReflectionUtils extends io.vertx.core.cli.impl.ReflectionUtils {

	/**
	 * 获取class的泛型类型
	 * 
	 * @param childClass
	 * @param index
	 * @return
	 * @see
	 */
	public static Class<?> getClassComponentType(Class<?> childClass, int index) {
		Type type = childClass.getGenericSuperclass();
		while (!(type instanceof Class)) {
			if (type instanceof WildcardType) {
				type = ((WildcardType) type).getUpperBounds()[0];
			} else if (type instanceof TypeVariable<?>) {
				type = ((TypeVariable<?>) type).getBounds()[0];
			} else if (type instanceof ParameterizedType) {
				ParameterizedType ptype = (ParameterizedType) type;
				Type[] types = ptype.getActualTypeArguments();
				if (types == null || types.length == 0) {
					return Object.class;
				}
				type = ptype.getActualTypeArguments()[0];
			} else if (type instanceof GenericArrayType) {
				type = ((GenericArrayType) type).getGenericComponentType();
			}
		}
		return (Class<?>) type;
	}

	/**
	 * 获取接口注解
	 *  
	 * @param clazz
	 * @param annotation
	 * @return
	 * @see
	 */
	public static Class<?> getAnnotationInterface(Class<?> clazz, Class<? extends Annotation> annotation) {
		if (clazz.getAnnotation(annotation) != null) {
			return clazz;
		}
		for (Class<?> iface : clazz.getInterfaces()) {
			if (iface.getAnnotation(annotation) != null) {
				return iface;
			}
		}
		if (clazz.getSuperclass() != null) {
			return getAnnotationInterface(clazz.getSuperclass(), annotation);
		}
		throw new VertxInvokeException(String.format("%s interface has not " + annotation + " annotation", clazz.getName()));
	}

}
