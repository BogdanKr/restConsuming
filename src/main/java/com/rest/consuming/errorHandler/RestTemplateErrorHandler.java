package com.rest.consuming.errorHandler;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus.Series;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

@Component
public class RestTemplateErrorHandler implements ResponseErrorHandler {

  private static final Logger LOG = LoggerFactory.getLogger(RestTemplateErrorHandler.class);

  @Override
  public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
    return (clientHttpResponse.getStatusCode().series() == Series.CLIENT_ERROR ||
        clientHttpResponse.getStatusCode().series() == Series.SERVER_ERROR);
  }

  @Override
  public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
    if (clientHttpResponse.getStatusCode().series() == Series.SERVER_ERROR) {
      //handleError
    } else if (clientHttpResponse.getStatusCode().series() == Series.CLIENT_ERROR) {
      //handleError
      if (clientHttpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
        throw new IllegalArgumentException("Entity not found");
      }
    }

  }
}
