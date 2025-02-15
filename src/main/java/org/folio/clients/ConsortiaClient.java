package org.folio.clients;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.folio.util.OkapiConnectionParams;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.URI;
import java.util.Objects;

import static org.folio.rest.RestVerticle.OKAPI_HEADER_TENANT;
import static org.folio.rest.RestVerticle.OKAPI_HEADER_TOKEN;

@Component
public class ConsortiaClient {

  private static final Logger logger = LogManager.getLogger(ConsortiaClient.class);

  private static final String USER_TENANTS_ENDPOINT = "/user-tenants?limit=1";

  public JsonArray getUserTenants(OkapiConnectionParams okapiConnectionParams) {
    var endpoint = okapiConnectionParams.getOkapiUrl() + USER_TENANTS_ENDPOINT;
    HttpGet httpGet = httpGet(okapiConnectionParams, endpoint);
    logger.info("Calling GET {}", endpoint);
    try (CloseableHttpResponse response = HttpClients.createDefault().execute(httpGet)) {
      var jsonObject = getResponseEntity(response);
      return jsonObject.getJsonArray("userTenants");
    } catch (Exception exception) {
      logger.error("Exception while calling {}", httpGet.getURI(), exception);
    }
    return new JsonArray();
  }

  @Cacheable
  public String getCentralTenantId(OkapiConnectionParams params) {
    var centralTenantIds = getUserTenants(params);
    if (!centralTenantIds.isEmpty()) {
      var centralTenantId = centralTenantIds.getJsonObject(0).getString("centralTenantId");
      if (centralTenantId.equals(params.getTenantId())) {
        logger.error("Current tenant is central");
      }
      return centralTenantId;
    }
    logger.info("No central tenant found");
    return StringUtils.EMPTY;
  }

  private HttpGet httpGet(OkapiConnectionParams okapiConnectionParams, String endpoint) {
    HttpGet httpGet = new HttpGet();
    httpGet.setHeader(OKAPI_HEADER_TOKEN, okapiConnectionParams.getToken());
    httpGet.setHeader(OKAPI_HEADER_TENANT, okapiConnectionParams.getTenantId());
    httpGet.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
    httpGet.setHeader((HttpHeaders.ACCEPT), MediaType.APPLICATION_JSON);
    httpGet.setURI(URI.create(endpoint));
    return httpGet;
  }

  private JsonObject getResponseEntity(CloseableHttpResponse response) throws IOException {
    HttpEntity entity = response.getEntity();
    var responseStatus = response.getStatusLine().getStatusCode();
    if (responseStatus == HttpStatus.SC_OK && Objects.nonNull(entity)) {
      try {
        var body = EntityUtils.toString(entity);
        logger.debug("Response body: {}", body);
        return new JsonObject(body);
      } catch (Exception e) {
        logger.error("Exception while building response entity", e);
      }
    }
    throw new IOException("Get invalid response with status: " + responseStatus);
  }
}

