package com.prajjwal.currencyconverter.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Currency Converter API",
                version = "1.0",
                description = "Currency Conversion REST API using FreeCurrencyAPI",
                contact = @Contact(
                        name = "Prajjwal",
                        email = "prajjwal@example.com"
                )
        )
)
public class OpenApiConfig {
}