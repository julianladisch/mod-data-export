package org.folio.clients;

import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.folio.TestUtil;
import org.folio.rest.HttpServerTestBase;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import java.util.Optional;
import java.util.UUID;

@RunWith(VertxUnitRunner.class)
class UsersClientUnitTest extends HttpServerTestBase {
  protected static final String USERS_BY_ID_URL = "/users/:id";
  protected static final String USER_RESPONSE_JSON = "mockData/user/get_user_response.json";

  @BeforeAll
  public static void beforeClass() throws Exception {
    setUpHttpServer();
    setUpMocks();
  }

  private static void setUpMocks() {
    String json = TestUtil.readFileContentFromResources(USER_RESPONSE_JSON);
    JsonObject data = new JsonObject(json);
    router.route(USERS_BY_ID_URL).method(HttpMethod.GET).handler(routingContext -> {
      HttpServerResponse response = routingContext.response();
      response.putHeader("content-type", "application/json");
      response.end(data.toBuffer());
    });
  }

  @Test
  void shouldReturnUserById() {
    // given
    UsersClient usersClient = new UsersClient();
    // when
    Optional<JsonObject> optionalUser = usersClient.getById(UUID.randomUUID().toString(), okapiConnectionParams);
    // then
    Assert.assertTrue(optionalUser.isPresent());
  }
}
