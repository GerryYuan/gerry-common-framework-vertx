package com.gerry.common.framework.vertx.handler;

import io.vertx.core.Handler;

import java.nio.charset.Charset;

public interface StandardInvokeHandler<E>  extends Handler<E>{
	
	public final static String DEFAULT_CONTENT_TYPE = "application/json;charset=UTF-8";
	
	public static final String DEFAULT_CHARSET = Charset.defaultCharset().name();
	
}
