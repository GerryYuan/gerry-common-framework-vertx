package com.gerry.common.framework.vertx.handler;

import io.vertx.core.http.HttpHeaders;
import io.vertx.ext.web.RoutingContext;

import java.nio.charset.Charset;

import lombok.extern.log4j.Log4j;

import com.gerry.common.framework.vertx.fastjson.VertxFastJsonMessageConverter;
import com.gerry.common.framework.vertx.http.helper.ViewModelHelper;

/**
 * 所有handler必须继承该抽象Handler
 * 
 *
 * @author gerry
 * @version 1.0.1, 2016年6月25日
 * @since com.gerry 1.0.1
 */
@Log4j
public abstract class AbstractInvokeHandler implements InvokeHandler<RoutingContext> {

	@Override
	public void handle(RoutingContext event) {
		event.response().headers().set(HttpHeaders.CONTENT_TYPE, DEFAULT_CONTENT_TYPE);
		event.response().setChunked(true);
		// 这边处理，需要执行的指定的方法
		try {
			event.response().write(VertxFastJsonMessageConverter.converter(invoke(event.request())), Charset.defaultCharset().name()).end();
		} catch (Exception e) {
			log.error("", e);
			event.response().setStatusCode(500).write(VertxFastJsonMessageConverter.converter(ViewModelHelper.NOViewModelResult(e.getMessage())), Charset.defaultCharset().name()).end();
		}
	}	

}
