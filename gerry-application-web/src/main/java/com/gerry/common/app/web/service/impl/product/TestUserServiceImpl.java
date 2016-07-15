package com.gerry.common.app.web.service.impl.product;

import com.gerry.common.app.web.dao.product.TestCrudRepository;
import com.gerry.common.app.web.service.product.UserService;
import com.gerry.common.framework.vertx.data.aop.AopCglibAutoProxy;

public class TestUserServiceImpl implements UserService {

	private TestCrudRepository testCrudRepository = (TestCrudRepository) AopCglibAutoProxy.createProxyInstance(TestCrudRepository.class);

	@Override
	public void delete(Long id) {
		testCrudRepository.delete(id, res -> {
			if (res > 0) {
				testCrudRepository.exists(id, isExists->{
					if(isExists){
						
					}
				});
			}
		});
	}

}
