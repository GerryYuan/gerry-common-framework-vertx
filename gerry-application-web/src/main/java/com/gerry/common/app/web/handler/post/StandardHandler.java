package com.gerry.common.app.web.handler.post;

import java.util.Set;

import lombok.extern.log4j.Log4j;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.FileUpload;

import com.gerry.common.framework.vertx.annotations.RouteHandler;
import com.gerry.common.framework.vertx.annotations.RouteMapping;
import com.gerry.common.framework.vertx.exception.VertxInvokeException;
import com.gerry.common.framework.vertx.handler.AbstractInvokeHandler;
import com.gerry.common.framework.vertx.handler.AbstractUploadFileInvokeHandler;
import com.gerry.common.framework.vertx.http.helper.ViewModelHelper;
import com.gerry.common.framework.vertx.http.result.ViewModelResult;

/**
 * 一个标准的Handler处理实现
 * 
 *
 * @author gerry
 * @version 1.0.1, 2016年6月25日
 * @since com.gerry 1.0.1
 */
@Log4j
@RouteHandler("/pro")
public class StandardHandler {

	@RouteMapping(method = HttpMethod.GET)
	public AbstractInvokeHandler products() {
		return new AbstractInvokeHandler() {
			@Override
			public ViewModelResult<?> invoke(HttpServerRequest resquest) throws VertxInvokeException {
				return ViewModelHelper.OKViewModelResult("welcome to vert.x");
			}
		};
	}

	@RouteMapping(value = "/:id", method = HttpMethod.GET)
	public AbstractInvokeHandler getProduct() {
		return new AbstractInvokeHandler() {
			@Override
			public ViewModelResult<?> invoke(HttpServerRequest resquest) throws VertxInvokeException {
				return ViewModelHelper.OKViewModelResult(resquest.getParam("id"));
			}
		};
	}

	@RouteMapping(value = "/upload", method = HttpMethod.POST)
	public AbstractUploadFileInvokeHandler upload() {
		return new AbstractUploadFileInvokeHandler() {
			@Override
			public ViewModelResult<?> invokeUpload(HttpServerRequest resquest, Set<FileUpload> files) throws VertxInvokeException {
				String filename = "";
				for (FileUpload file : files) {
					String path = file.uploadedFileName();
					log.info("upload path : " + path);
					filename = path.substring(path.lastIndexOf("\\") + 1);
				}
				return ViewModelHelper.OKViewModelResult(filename);
			}
		};
	}

}
