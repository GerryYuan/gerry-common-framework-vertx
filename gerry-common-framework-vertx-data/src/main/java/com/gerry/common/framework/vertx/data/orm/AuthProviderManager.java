package com.gerry.common.framework.vertx.data.orm;

import io.vertx.ext.auth.AuthProvider;
import io.vertx.ext.auth.jdbc.JDBCAuth;

public class AuthProviderManager {
	
	public static final AuthProvider getAuthProvider(){
			return JDBCAuth.create(VertxJDBCManager.jdbcClient);
	}
	
}
