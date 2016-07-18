package com.gerry.common.framework.vertx.data.orm;

import org.jfaster.mango.operator.Mango;

public interface MangoManager {

	static Mango getInstance() {
		MangoConfiguration.init();
		return MangoConfiguration.mango;
	}
}
