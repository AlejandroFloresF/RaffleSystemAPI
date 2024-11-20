package com.example.rafflesystemapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Asegúrate de que el patrón de rutas sea correcto para tu API
        registry.addMapping("/api/**") // Ajusta este patrón para que coincida con tus rutas
                .allowedOrigins("http://localhost:3000") // Origen de tu frontend (React, por ejemplo)
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos permitidos
                .allowedHeaders("*") // Permite todos los encabezados
                .allowCredentials(true); // Permite el envío de cookies (si es necesario)
    }
}
