package com.cubic.springbootcruddemo.config;


import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    public OpenAPI springOpenApi(){

       return new OpenAPI()
               .info(new Info().title("Spring Boot Rest Demo")
                    .description("Spring Boot Rest API Documentation")
                    .version("v.1")
                    .license(new License().name("Apache 2.0").url("http://springdoc.org")))
               .externalDocs(new ExternalDocumentation()
                        .description("Spring Boot WIKI Documentation")
                        .url("https://springdoc.org"));

    }
}
