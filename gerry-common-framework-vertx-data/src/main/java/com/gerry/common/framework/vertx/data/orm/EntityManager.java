package com.gerry.common.framework.vertx.data.orm;

import io.vertx.core.shareddata.LocalMap;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

import org.reflections.Reflections;

import com.gerry.common.framework.vertx.data.annotations.Column;
import com.gerry.common.framework.vertx.data.annotations.Table;
import com.gerry.common.framework.vertx.utils.ReflectionUtils;
import com.gerry.common.framework.vertx.utils.SharedDataUtils;
import com.google.common.collect.Maps;

/**
 * 扫描注解table的类，设置entity属性，每个类对应一个map，key：class，value：entrty值
 * 
 *
 * @author gerry
 * @version 1.0.1, 2016年7月3日
 * @since com.gerry 1.0.1
 */
public class EntityManager {

	private static final Reflections reflections = new Reflections("com.gerry.common.app.web.entity.product");

	private static final String SQL_LOCAL_MAP_NAME = "__vertx.EntityManager.sqls";

	public final static LocalMap<String, Entity> localMap = SharedDataUtils.sharedData.getLocalMap(SQL_LOCAL_MAP_NAME);

	static Set<Class<?>> tables = reflections.getTypesAnnotatedWith(Table.class);

	// 这边是一个entity的一些设置的table's property
	static ConcurrentMap<Class<?>, Entity> tableMap = Maps.newConcurrentMap();

	static {
		for (Class<?> table : tables) {
			Field[] fields = table.getDeclaredFields();
			tableMap.put(table, convertEntity(table, fields));
		}
	}

	private static Entity convertEntity(Class<?> table, Field... fields) {
		Entity entity = new Entity();
		entity.setClassName(table.getName());
		entity.setTable(table.getAnnotation(Table.class).value());
		for (Field field : fields) {
			annotationValue(entity, field);
		}
		return entity;
	}

	private static void annotationValue(Entity entity, Field field) {
		if (field.isAnnotationPresent(Column.class)) {
			entity.getColumns().add(field.getAnnotation(Column.class).value());
		}
	}

	/**
	 * 获取class的注解entity
	 * 
	 * @param clazz
	 * @return
	 * @see
	 */
	private static Entity getClassEntity(Class<?> clazz) {
		return tableMap.get(clazz);
	}

	/**
	 * 替换接口注解的变量
	 * 
	 * @param methodAnnotationValue
	 * @param entity
	 * @return
	 * @see
	 */
	public static void convertSql(String methodAnnotationValue, Class<?> clazz) {
		Objects.requireNonNull(methodAnnotationValue, " method annoration value must not be null");
		Objects.requireNonNull(clazz, " clazz must not be null");
		Entity entity = getClassEntity(ReflectionUtils.getClassComponentType(clazz, 0));
		Objects.requireNonNull(entity, " entity is not exists maybe entity is not annotation table");
		entity.setSql(methodAnnotationValue.replace("#", " " + entity.getTable() + " "));
		localMap.put(clazz.getName() + "_" + Thread.currentThread().getName(), entity);
	}
}
