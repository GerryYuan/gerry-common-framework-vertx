package com.gerry.common.framework.vertx.utils;

import io.vertx.core.shareddata.SharedData;

public class SharedDataUtils extends VertxCoreUtils{

	public static SharedData sharedData = vertx.sharedData();

}
