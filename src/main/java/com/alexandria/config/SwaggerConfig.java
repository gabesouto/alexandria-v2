package com.alexandria.config;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.*;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
public class SwaggerConfig {

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .info(new Info()
            .title("Alexandria API")
            .version("1.0")
            .description(
                "Welcome to the Alexandria API, a RESTful service designed to manage the core entities of a digital library system. This API allows users to handle user registration, authentication, author management, and book operations with full CRUD capabilities.\n"
                    + "\n"));
  }
}
