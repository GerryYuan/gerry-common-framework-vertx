package com.gerry.common.framework.vertx.data.repository;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.UpdateResult;

import com.gerry.common.framework.vertx.data.annotations.Delete;
import com.gerry.common.framework.vertx.data.annotations.Repository;
import com.gerry.common.framework.vertx.data.annotations.Selector;
import com.gerry.common.framework.vertx.data.handler.ResultSetInvokeHandler;
import com.gerry.common.framework.vertx.data.handler.UpdateResultInvkeHandler;

@Repository
public interface CrudRepository<T, ID, E>  {

	@Selector(" SELECT * FROM # ")
	void findAll(ResultSetInvokeHandler<AsyncResult<ResultSet>> handler);

	@Selector(" SELECT * FROM #  WHERE id = ? ")
	void getOne(ID id, ResultSetInvokeHandler<AsyncResult<ResultSet>> handler);

	<S extends T> void save(S entity);

	@Selector(" SELECT * FROM # WHERE id = ? ")
	void exists(ID id, Handler<Boolean> handler);

	long count();

	@Delete("DELETE FROM # WHERE id = ? ")
	void delete(ID id, Handler<Integer> handler);
	
	@Delete("DELETE FROM # ")
	void deleteAll(UpdateResultInvkeHandler<AsyncResult<UpdateResult>> handler);
	
}
