package com.gerry.common.framework.vertx.http.result;

import com.gerry.common.framework.vertx.utils.VertxEmptyUtils;


/**
 * 返回客户端结构实体映射
 * 
 *
 * @author gerry
 * @version 1.0 2015年8月6日
 * @since com.gerry
 */
public class ViewModelResult<T> extends Result<T> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2751613606306765518L;
	
	public ViewModelResult() {
		super.setSuccess(true);
		super.setMessage("执行成功");
	}
	
	public ViewModelResult(String... message) {
		super.setSuccess(false);
		if (VertxEmptyUtils.isEmpty(message)) {
			super.setMessage("执行失败");
		} else {
			super.setMessage(message[0]);
		}
	}
	
	public ViewModelResult(T result) {
		super.setSuccess(true);
		super.setMessage("执行成功");
		super.setResult(result);
	}

	public ViewModelResult(T result, String... message) {
		super.setSuccess(false);
		if (VertxEmptyUtils.isEmpty(message)) {
			super.setMessage("执行失败");
		} else {
			super.setMessage(message[0]);
		}
		super.setResult(result);
	}

}
