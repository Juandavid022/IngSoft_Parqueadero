package com.mvcproject.v1.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Autos Colombia",
        version = "1.0",
        description = "API para gestión de Parqueadero"
    ),
    servers = @Server(url = "/")
)
public class OpenApiConfig {

    /**
     * Define un bean de OpenAPI que configura la información básica de la API.
     * Este bean se utilizará para generar la documentación de la API.
     *
     * @return un objeto OpenAPI configurado con la información de la API.
     */
    @Bean 
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info() // Crea una nueva instancia de Info para describir la API
                        .title("Parqueadero API") // Establece el título de la API
                        .version("1.0") // Establece la versión de la API
                        .description("API para gestión de Autos Colombia")); // Establece una descripción de la API
    }
}
