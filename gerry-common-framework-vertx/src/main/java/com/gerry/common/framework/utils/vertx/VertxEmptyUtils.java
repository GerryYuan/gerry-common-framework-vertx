package com.gerry.common.framework.utils.vertx;

import java.util.Collection;
import java.util.Map;

/**
 * 	判空工具类
 *
 * @author gerry
 * @version  1.0 2015年8月6日
 * @since   com.gerry
 */
public final class VertxEmptyUtils {

	/**
	 * 判断集合是否为空 coll->null->true coll-> coll.size() == 0 -> true
	 * 
	 * @param coll
	 * @return
	 */
	public static <T> boolean isEmpty(Collection<T> coll) {
		return (coll == null || coll.isEmpty());
	}

	/**
	 * 判断集合是否不为空
	 * 
	 * @param coll
	 * @return
	 */
	public static <T> boolean isNotEmpty(Collection<T> coll) {
		return !isEmpty(coll);
	}

	/**
	 * 判断map是否为空
	 * 
	 * @param map
	 * @return
	 */
	public static <K, V> boolean isEmpty(Map<K, V> map) {
		return (map == null || map.isEmpty());
	}

	/**
	 * 判断map是否不为空
	 * 
	 * @param map
	 * @return
	 */
	public static <K, V> boolean isNotEmpty(Map<K, V> map) {
		return (map == null || map.isEmpty());
	}

	/**
	 * 判断一个对象是否为空
	 * 
	 * @param t
	 * @return
	 */
	public static <T> boolean isEmpty(T t) {
		if (t == null) {
			return true;
		}
		if (t.toString().equals("")) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断数组是否不为空
	 * 
	 * */
	public static <T> boolean isNotEmpty(T[] datas) {
		if ( datas == null || datas.length == 0) {
				return false;
		}
		return true;
	}
	
	/**
	 * 判断数组是否不为空
	 * 
	 * */
	public static <T> boolean isEmpty(T[] datas) {
		return !isNotEmpty(datas);
	}
	

	/**
	 * 判断一个对象是否不为空
	 * 
	 * @param t
	 * @return
	 */
	public static <T> boolean isNotEmpty(T t) {
		return !isEmpty(t);
	}

}
