package com.gerry.common.framework.vertx.handler;

import io.vertx.core.http.HttpHeaders;
import io.vertx.ext.web.RoutingContext;

import java.nio.charset.Charset;

import lombok.extern.log4j.Log4j;

import com.gerry.common.framework.vertx.fastjson.VertxFastJsonMessageConverter;
import com.gerry.common.framework.vertx.http.helper.ViewModelHelper;

@Log4j
public abstract class AbstractUploadFileInvokeHandler implements UploadFileInvokeHandler<RoutingContext> {

	@Override
	public void handle(RoutingContext event) {
		event.response().headers().set(HttpHeaders.CONTENT_TYPE, DEFAULT_CONTENT_TYPE);
		event.response().setChunked(true);
		// 这边处理，需要执行的指定的方法
		try {
			event.response().write(VertxFastJsonMessageConverter.converter(invokeUpload(event.request(), event.fileUploads())), Charset.defaultCharset().name()).end();
		} catch (Exception e) {
			log.error("", e);
			event.response().setStatusCode(500).write(VertxFastJsonMessageConverter.converter(ViewModelHelper.NOViewModelResult(e.getMessage())), Charset.defaultCharset().name()).end();
		}
	}

}
