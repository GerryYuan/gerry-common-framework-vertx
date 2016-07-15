package com.gerry.common.framework.vertx.data.orm;

import io.vertx.core.shareddata.Shareable;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com.google.common.collect.Lists;

@Setter
@Getter
public class Entity implements Shareable{

	private List<String> columns = Lists.newArrayList();
	
	private String table;
	
	private String className;
	
	private String sql;
	
}
