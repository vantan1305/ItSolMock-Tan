package com.demo.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.UrlBasedViewResolverRegistration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer ( ) {
            @Override
            public void addCorsMappings(CorsRegistry registry){
                registry.addMapping ("/**")
                        .allowedMethods ("GET","POST", "PUT", "DELETE", "OPTION","HEAD","PUT","PATCH")
                        .allowedHeaders ("*")
                        .allowedOrigins ("http://localhost:4200");
            }
        };
    }
}
