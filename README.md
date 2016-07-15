#gerry-common-framework-vertx
Vert.x 3.2.1 Web Application 
 一个类似于spring framework web的注解式application，0基础实战vertx-web，注解式开发，0成本spring过度vertx
 
 CRUD
 
 Validation, right now there's no input validation
 
 Upload File sample （OK）
 
 Websocket sample, eg: provide chatting box for logged in user
 
 EventBus sample
 
 EventBus RPC （OK）

开发REST API步骤：
    
    第一步首先：引入gerry-common-framework-vertx模块
    
    <dependency>
  		<groupId>gerry-common-framework-vertx</groupId>
  		<artifactId>gerry-common-framework-vertx</artifactId>
  		<version>0.0.1-SNAPSHOT</version>
  	</dependency>
  	
  	第二步：在项目模块的src/main/resources下添加名字为config.json，log4j.properties的文件，配置如下
  	
    {
      "port" : 8080,
      "scan.route.url":"com.gerry.common.app.web.handler.*",
      "upload.files.url":"files"
    }
    
    port:端口
    scan.route.url：配置的路由扫描路劲，和spring扫描controller一样
    upload.files.url：上传文件路径
    
    log4j.properties：就是一个日志的文件配置
    
    第三步：
    添加一个REST API,
    
    
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
    
    需要注意的是所有方法方式必须返回AbstractInvokeHandler抽象类，在invoke方法种进行参数解析，调用service，目前只做了Http层封装，还没有对service层进行封装
    
    操作到文件时，方法需要返回AbstractUploadFileInvokeHandler抽象类，在invokeUpload方法种获取文件对象。
    
    然后运行，StartApplicationVertxServer类就可以了，一个类似于spring风格的REST API就可以了.....

