package gerry_application_web.gerry_application_web;

import java.io.IOException;

import com.gerry.common.framework.vertx.data.orm.VertxJDBCManager;
import com.gerry.common.framework.vertx.server.StartVertxWebServer;

public class StartApplicationVertxServer {

	public static void main(String[] args) throws IOException {
		StartVertxWebServer.main(new String[]{""});
		VertxJDBCManager.init();
	}
}
