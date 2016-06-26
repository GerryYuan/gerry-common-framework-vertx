package com.gerry.common.framework.http.helper.vertx;

import com.gerry.common.framework.http.result.vertx.Result;
import com.gerry.common.framework.http.result.vertx.ViewModelResult;

/**
 * 返回客户端实体帮助类
 * 
 *
 * @author gerry
 * @version 1.0 2015年8月6日
 * @since com.gerry
 */
public class ViewModelHelper {

	public static <T> ViewModelResult<T> NOViewModelResult(T result, String message) {
		return new ViewModelResult<T>(result, message);
	}
	
	public static <T> ViewModelResult<T> NOViewModelResult(String... message) {
		return new ViewModelResult<T>(message);
	}
	
	public static <T> ViewModelResult<T> OKViewModelResult(T result) {
		return new ViewModelResult<T>(result);
	}
	
	public static <T> ViewModelResult<T> OKViewModelResult() {
		return new ViewModelResult<T>();
	}
	
	public static <T> Result<T> toResult(T result) {
		return new Result<T>(result);
	}

}
