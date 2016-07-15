package com.gerry.common.app.web.dao.product;

import io.vertx.core.AsyncResult;
import io.vertx.ext.sql.ResultSet;

import com.gerry.common.app.web.entity.product.User;
import com.gerry.common.framework.vertx.data.annotations.Repository;
import com.gerry.common.framework.vertx.data.repository.support.SimpleCrudRepository;

@Repository
public class TestCrudRepository extends SimpleCrudRepository<User, Long, AsyncResult<ResultSet>>{

	
}
