package br.com.jzpacheco.restspringbootandjavaerudio.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("RESTful API with Java and Spring")
                .version("v1")
                .description("API for study purposes")
                .termsOfService("https://github.com/jzpacheco")
                .license(new License()
                        .name("Apache 2.0")
                        .url("https://github.com/jzpacheco")));
    }
}
