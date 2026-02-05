package com.gft.pricechecker.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI priceCheckerOpenAPI() {
        return new OpenAPI().info(new Info().title("Price Checker API")
                        .description("API to retrieve applicable product prices by date, brand and product")
                        .version("1.0.0"));
    }
}
