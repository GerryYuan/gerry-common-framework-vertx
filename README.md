#gerry-common-framework-vertx

 `Vert.x-Web` 3.2.1 类似于`spring` `framework` `web`，不依赖任何spring模块，`注解式`开发的Application
 
 `Vertx-RPC` 3.2.1 `远程调用`
 
 
 模块功能：
 
 	gerry-common-framework-vertx，vertx核心封装功能，所有模块需依赖该模块
 	
 	gerry-common-framework-vertx-data，主要做和数据库相关的接口封装，目前还在开发中....
 	
 	gerry-common-framework-vertx-rpc，通过eventbus进行rpc调用，目前大体功能实现差不多，还有些功能在完善中.....
 	
 	gerry-common-service，模拟一个类似的service接口，进行rpc调用，前期放在这个模块里面，后面会移植出去.....
 	
 	gerry-application-web，一个类似于spring web的注解式rest api封装，具体看下面说明
 	
 后面主要的工作：
 
 	进行vertx 集群封装，以及数据库DAO层封装，以及redis封装，目前所有对接数据仓库的模块都放在vertx-data中（redis，jdbc）
 
 CRUD
 
 Validation, right now there's no input validation
 
 Upload File sample （OK）
 
 Websocket sample, eg: provide chatting box for logged in user
 
 EventBus sample
 
 EventBus RPC （OK）

开发REST API步骤：
    
  第一步首先：引入gerry-common-framework-vertx模块
  
```xml
<dependency>
  <groupId>gerry-common-framework-vertx</groupId>
  <artifactId>gerry-common-framework-vertx</artifactId>
  <version>0.0.1-SNAPSHOT</version>
</dependency>
```
    
    
  第二步：在项目模块的src/main/resources下添加名字为config.json，log4j.properties的文件，配置如下
  
```json	
{
"port" : 8080,
"scan.route.url":"com.gerry.common.app.web.handler.*",
"upload.files.url":"files"
}
``` 
    port:端口
    scan.route.url：配置的路由扫描路劲，和spring扫描controller一样
    upload.files.url：上传文件路径
    
    log4j.properties：就是一个日志的文件配置
    
  第三步： 添加一个REST API,
    
```java
@Log4j
@RouteHandler("/welcome")
public class StandardHandler {
    @RouteMapping(method = HttpMethod.GET)
    public AbstractInvokeHandler products() {
    	return new AbstractInvokeHandler() {
    		@Override
    		public ViewModelResult<?> invoke(HttpServerRequest resquest) throws VertxInvokeException {
    			return ViewModelHelper.OKViewModelResult("welcome to vert.x");
    		}
    	};
    }
    
    @RouteMapping(value = "/upload", method = HttpMethod.POST)
    public AbstractUploadFileInvokeHandler upload() {
	return new AbstractUploadFileInvokeHandler() {
		@Override
		public ViewModelResult<?> invokeUpload(HttpServerRequest resquest, Set<FileUpload> files) throws VertxInvokeException {
			String filename = "";
			for (FileUpload file : files) {
				String path = file.uploadedFileName();
				log.info("upload path : " + path);
				filename = path.substring(path.lastIndexOf("\\") + 1);
			}
			return ViewModelHelper.OKViewModelResult(filename);
		}
	};
}
}
```
  
  注意:所有方法方式必须返回AbstractInvokeHandler抽象类，在invoke方法种进行参数解析，调用service，目前只做了Http层封装，还没有对service层进行封装
    
    操作到文件时，方法需要返回AbstractUploadFileInvokeHandler抽象类，在invokeUpload方法种获取文件对象。
    
    然后运行，StartApplicationVertxServer类就可以了，一个类似于spring风格的REST API就可以了.....

