package com.gerry.service.impl.user;

import java.util.concurrent.CompletableFuture;

import lombok.extern.log4j.Log4j;

import com.gerry.common.framework.eventbus.annotations.EventBusServiceProvider;
import com.gerry.service.user.UserService;

@Log4j
@EventBusServiceProvider
public class UserServiceImpl implements UserService {

	@Override
	public String save(String username) {
		log.info(username + "======");
		return username;
	}

	@Override
	public CompletableFuture<String> delete(int id) {
		log.info(id);
		CompletableFuture<String> future = new CompletableFuture<>();
		future.complete("delete");
		return future;
	}

	@Override
	public void add() {
		log.info("add");
	}
}
