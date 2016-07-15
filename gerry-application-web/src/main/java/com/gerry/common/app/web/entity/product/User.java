package com.gerry.common.app.web.entity.product;

import lombok.Getter;
import lombok.Setter;

import com.gerry.common.framework.vertx.data.annotations.Column;
import com.gerry.common.framework.vertx.data.annotations.Table;

@Table("user")
@Setter
@Getter
public class User {
	
	@Column("id")
	private int id;
	
	@Column("username")
	private String username;
	
	@Column("password")
	private String password;
	
}
