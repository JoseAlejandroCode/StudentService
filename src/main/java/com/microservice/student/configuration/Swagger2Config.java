package com.microservice.student.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;

import java.util.Collections;
import java.util.Optional;

@Configuration
@EnableSwagger2WebFlux
public class Swagger2Config {
  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors
                    .basePackage("com.microservice.student.controller"))
            .paths(PathSelectors.regex("/api/students.*"))
            .build()
            .genericModelSubstitutes(Mono.class, Flux.class, Optional.class)
            .apiInfo(apiEndPointsInfo());
  }
  private ApiInfo apiEndPointsInfo() {
    return new ApiInfo("Estudent Microservice",
            "Estudent Management REST API", "1.0", "" ,
            new Contact("Jose Alejandro Paredes Paredes", "", "jparedpa@everis.com"),
                    "", "", Collections.emptyList());
  }
}