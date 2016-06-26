package com.gerry.common.framework.http.result.vertx;

import java.io.Serializable;

/**
 * 输出客户端实体类
 * 
 *
 * @author gerry
 * @version  1.0 2015年8月6日
 * @since   com.gerry
 */
public class Result<T> implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8886624063083045314L;

	public Result() {

	}

	public Result(T result) {
		this.setResult(result);
	}
	
	private T result;

	private boolean success;
	
	private String message;
	
	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
