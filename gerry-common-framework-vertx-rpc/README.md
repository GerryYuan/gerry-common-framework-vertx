#gerry-common-framework-vertx-rpc
`Vert.x` 3.2.1 `EventBus` `Vertx-RPC`

基于`EventBus`的`RPC` 

开发EventBus RPC步骤：
     	
1.interface：客户端需要依赖service接口模块，接口规范必须是@EventBusServiceClient注解的接口，方法上必须是@Consumer注解，默认调用eventbus的send

```java	
@EventBusServiceClient
public interface UserService {

	@Consumer
	String save(String username);

	@Consumer
	CompletableFuture<String> delete(int id);

	@Consumer
	void add();
}
```

2.implementation：实现，这个是注册时需要在实现类上添加@EventBusServiceProvider（扫描）
	
```java	
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
```

3.example：一个简单的RPC调用就ok了

```java
public class TestRPC {

	public static void main(String[] args) throws Exception {
		VertxRPCUtils.rpcFactory.registerServer(VertxCoreUtils.vertx.eventBus());//注册eventbus server

		UserService userService = VertxRPCUtils.rpcFactory.createClient(VertxCoreUtils.vertx.eventBus(), 

		UserService.class);//通过eventbus创建接口对象

		userService.save("xxx");//调用接口方法
}
```

4.配置：

```json
/src/main/resources/config.json中需要配置接口实现类的扫描路劲

{
  "scan.service.url":"com.gerry.service.impl"
}
```
