package com.gerry.common.web.test;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestSuite;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.log4j.Log4j;

@Log4j
public class WebTest {

	private static Vertx vertx = Vertx.vertx();

	static ExecutorService executorService = Executors.newCachedThreadPool();

	public static void main(String[] args) throws InterruptedException {
		TestSuite suite = TestSuite.create("the_test_suite");
		suite.test("my_test_case", context -> {
			Async async = context.async();
			HttpClient client = vertx.createHttpClient();
			for (;;) {
				try {
					long start1 = System.currentTimeMillis();
					HttpClientRequest req = client.delete(8080, "127.0.0.1", "/user/delete?id=1", res->{
						res.exceptionHandler(err -> context.fail(err.getMessage()));
						context.assertEquals(200, res.statusCode());
						async.complete();
					});
					/*HttpClientRequest req = client.get(8080, "localhost", "/pro/12");
					req.exceptionHandler(err -> context.fail(err.getMessage()));
					req.handler(resp -> {
						long start = System.currentTimeMillis();
						context.assertEquaSignaturels(200, resp.statusCode());
						log.info(" invoke /pro/12 ok , response (" + 1 + ")");
						async.complete();
						log.info("invoke /pro/12 " + (System.currentTimeMillis() - start) + "ms");
					});*/
					req.end();
					log.info("invoke " + (System.currentTimeMillis() - start1) + "ms");
					Thread.sleep(10000);
				} catch (Exception e) {

				}

			}
		});
		suite.run();
	}
}
