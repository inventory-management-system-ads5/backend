package com.application.ims.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("ims API")
                        .version("1.0.0")
                        .description("Demo application to handle the basic usages of an Inventory Management System.")
                        .license(new License()
                                .name("Project's repo")
                                .url("https://github.com/inventory-management-system-ads5/backend")));
    }
}
