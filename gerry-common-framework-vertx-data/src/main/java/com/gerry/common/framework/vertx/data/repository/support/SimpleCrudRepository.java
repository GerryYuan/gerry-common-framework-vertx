package com.gerry.common.framework.vertx.data.repository.support;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.UpdateResult;

import java.util.Objects;

import com.gerry.common.framework.vertx.data.annotations.Repository;
import com.gerry.common.framework.vertx.data.handler.ResultSetInvokeHandler;
import com.gerry.common.framework.vertx.data.handler.UpdateResultInvkeHandler;
import com.gerry.common.framework.vertx.data.orm.EntityManager;
import com.gerry.common.framework.vertx.data.orm.VertxJDBCManager;
import com.gerry.common.framework.vertx.data.repository.CrudRepository;
import com.gerry.common.framework.vertx.utils.StringUtils;

@Repository
public class SimpleCrudRepository<T, ID, E> implements CrudRepository<T, ID, E> {

	private static final String ID_MUST_NOT_BE_NULL = "The given id must not be null!";

	@Override
	public void delete(ID id, Handler<Integer> handler) {
		Objects.requireNonNull(id, ID_MUST_NOT_BE_NULL);
		String sql = EntityManager.localMap.get(this.getClass().getSuperclass().getName() + "_" + Thread.currentThread().getName()).getSql();
		VertxJDBCManager.INSTANCE.delete(StringUtils.replace(sql, id), handler);
	}

	@Override
	public void deleteAll(UpdateResultInvkeHandler<AsyncResult<UpdateResult>> handler) {

	}

	@Override
	public void findAll(ResultSetInvokeHandler<AsyncResult<ResultSet>> handler) {

	}

	@Override
	public <S extends T> void save(S entity) {

	}

	@Override
	public void exists(ID id, Handler<Boolean> handler) {
		Objects.requireNonNull(id, ID_MUST_NOT_BE_NULL);
		String sql = EntityManager.localMap.get(this.getClass().getSuperclass().getName() + "_" + Thread.currentThread().getName()).getSql();
		VertxJDBCManager.INSTANCE.exists(StringUtils.replace(sql, id), handler);
	}

	@Override
	public long count() {
		return 0;
	}

	@Override
	public void getOne(ID id, ResultSetInvokeHandler<AsyncResult<ResultSet>> handler) {

	}

}
