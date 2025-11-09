package br.com.fiap.Portaria.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Portaria",
                version = "v2",
                description = "API Para gerênciamento de um aplicativo de portaria",
                contact = @Contact(name = "Portaria", email = "rm560179@fiap.com.br")
        ),
        servers = {@Server(url = "http://localhost:8080", description = "LOCAL")},
        tags = {@Tag(name = "Portaria", description = "Sistema de entregas de encomendas")}
)



public class SwaggerConfig{

}