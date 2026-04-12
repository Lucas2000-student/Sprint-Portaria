package br.com.fiap.Portaria.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Portaria",
                version = "v2",
                description = "API para gerenciamento de portaria de condomínio",
                contact = @Contact(name = "Portaria", email = "rm560179@fiap.com.br")
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "LOCAL"),
                @Server(url = "http://portaria-app.hrgjf6h8amccbhf7.brazilsouth.azurecontainer.io:8080", description = "AZURE")
        },
        tags = {@Tag(name = "Portaria", description = "Sistema de entregas de encomendas")}
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "Firebase Token"
)
public class SwaggerConfig {
}