package org.folio.rest;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import org.folio.rest.tools.utils.NetworkUtils;
import org.folio.util.OkapiConnectionParams;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static org.folio.rest.RestVerticle.OKAPI_HEADER_TENANT;
import static org.folio.rest.RestVerticle.OKAPI_HEADER_TOKEN;

/**
 * Class for tests that base on testing code using HTTP mock server
 */
public abstract class HttpServerTestBase {

  protected static final String TENANT_ID = "diku";
  protected static final String TOKEN = "token";
  private static final String HOST = "http://localhost:";
  protected static Vertx vertx;
  protected static Router router;
  protected static OkapiConnectionParams okapiConnectionParams;
  private static HttpServer httpServer;

  @BeforeClass
  public static void setUpHttpServer() {
    vertx = Vertx.vertx();
    int port = NetworkUtils.nextFreePort();
    router = Router.router(vertx);
    httpServer = vertx.createHttpServer();
    CompletableFuture<HttpServer> deploymentComplete = new CompletableFuture<>();
    httpServer.requestHandler(router).listen(port, result -> {
      if(result.succeeded()) {
        deploymentComplete.complete(result.result());
      }
      else {
        deploymentComplete.completeExceptionally(result.cause());
      }
    });

    Map<String, String> okapiHeaders = new HashMap<>();
    okapiHeaders.put("x-okapi-url", HOST + port);
    okapiHeaders.put(OKAPI_HEADER_TENANT, TENANT_ID);
    okapiHeaders.put(OKAPI_HEADER_TOKEN, TOKEN);
    okapiConnectionParams = new OkapiConnectionParams(okapiHeaders);
  }

  @AfterClass
  public static void tearDownClass() {
    httpServer.close();
  }
}
