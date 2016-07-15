package com.gerry.common.framework.vertx.handler;

import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.FileUpload;

import java.util.Set;

import com.gerry.common.framework.vertx.exception.VertxInvokeException;
import com.gerry.common.framework.vertx.http.result.ViewModelResult;

/**
 * 上传文件
 * 
 *
 * @author gerry
 * @version  1.0.1, 2016年6月26日
 * @since   com.gerry 1.0.1
 */
public interface UploadFileInvokeHandler<E> extends StandardInvokeHandler<E> {

	ViewModelResult<?> invokeUpload(HttpServerRequest resquest, Set<FileUpload> files) throws VertxInvokeException;
	
}
