package com.srijan.pixelcypher.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        
        // Allow requests from your Next.js application domain
        corsConfiguration.addAllowedOrigin("https://pixelcypher-app.vercel.app"); // Next.js dev server
        
        // Allow common HTTP methods
        corsConfiguration.addAllowedMethod("GET");
        corsConfiguration.addAllowedMethod("POST");
        corsConfiguration.addAllowedMethod("PUT");
        corsConfiguration.addAllowedMethod("DELETE");
        corsConfiguration.addAllowedMethod("OPTIONS");
        
        // Allow all headers
        corsConfiguration.addAllowedHeader("*");
        
        // Allow credentials (cookies, authorization headers, etc.)
        corsConfiguration.setAllowCredentials(true);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", corsConfiguration); // Apply to all endpoints under /api/
        
        return new CorsFilter(source);
    }
}