package com.project.kitsuapi;

import com.project.kitsuapi.config.AppConfiguration;
import com.project.kitsuapi.config.SwaggerConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class kitsuApiApplication {

  public static void main(String[] args) {
    new SpringApplicationBuilder(
        kitsuApiApplication.class,
        AppConfiguration.class,
        SwaggerConfiguration.class
    ).run();
  }

}
