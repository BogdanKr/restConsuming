package com.rest.consuming;

import com.rest.consuming.entity.Conference;
import com.rest.consuming.errorHandler.RestTemplateErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestConsumingApp {

  private static final Logger LOG = LoggerFactory.getLogger(RestConsumingApp.class);

  public static void main(String[] args) {
    SpringApplication.run(RestConsumingApp.class, args);
  }

  @Bean
  public ResponseErrorHandler responseErrorHandler() {
    return new RestTemplateErrorHandler();
  }

  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder templateBuilder) {
    return templateBuilder
        .errorHandler(responseErrorHandler())
        .build();
  }

  @Bean
  public CommandLineRunner run(RestTemplate restTemplate) {
    return args -> {
      try {
        ResponseEntity<Conference> responseEntity = restTemplate
            .getForEntity("http://localhost:8080/conferences/2", Conference.class);
        Conference conference = responseEntity.getBody();
        LOG.info("LOG info: Consuming REST, Conference: " + conference);
      } catch (IllegalArgumentException e) {
        LOG.error("Error while consuming: " + e.getMessage());
      }
    };
  }

}
