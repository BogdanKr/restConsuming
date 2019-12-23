package com.rest.consuming;

import com.rest.consuming.entity.Conference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestConsumingApp {

  private static final Logger LOG = LoggerFactory.getLogger(RestConsumingApp.class);

  public static void main(String[] args) {
    SpringApplication.run(RestConsumingApp.class, args);
  }

  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder templateBuilder) {
    return templateBuilder.build();
  }

  @Bean
  public CommandLineRunner run(RestTemplate restTemplate) {
    return args -> {
      Conference conference = restTemplate
          .getForObject("http://localhost:8080/conferences/3", Conference.class);
      LOG.info("LOG info: Consuming REST, Conference: " + conference);
    };
  }

}
