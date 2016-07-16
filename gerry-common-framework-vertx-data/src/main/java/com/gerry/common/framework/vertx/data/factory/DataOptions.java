package com.gerry.common.framework.vertx.data.factory;

import io.vertx.core.ServiceHelper;

/**
 * 数据工厂操作接口
 * 
 *
 * @author gerry
 * @version  1.0, 2016年7月16日下午3:41:55
 * @since   com.gerry.link 1.0
 */
public interface DataOptions {
	
	static  DataFactory<?> newData() {
		return (DataFactory<?>) factory.newFactory();
	}

	static final DataFactory<?> factory = ServiceHelper.loadFactory(DataFactory.class);
	
}
