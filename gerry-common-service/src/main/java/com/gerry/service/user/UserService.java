package com.gerry.service.user;

import java.util.concurrent.CompletableFuture;

import com.gerry.common.framework.eventbus.annotations.Consumer;
import com.gerry.common.framework.eventbus.annotations.EventBusServiceClient;

@EventBusServiceClient
public interface UserService {

	@Consumer
	String save(String username);

	@Consumer
	CompletableFuture<String> delete(int id);

	@Consumer
	void add();
}
