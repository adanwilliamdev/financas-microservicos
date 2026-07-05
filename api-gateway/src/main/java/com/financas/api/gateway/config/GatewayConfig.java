package com.financas.api.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("auth-service", r -> r
                .path("/api/auth/**")
                .filters(f -> f.stripPrefix(0))
                .uri("http://localhost:8081"))
            .route("transaction-service", r -> r
                .path("/api/transacoes/**")
                .filters(f -> f.stripPrefix(0))
                .uri("http://localhost:8082"))
            .route("report-service", r -> r
                .path("/api/relatorios/**")
                .filters(f -> f.stripPrefix(0))
                .uri("http://localhost:8083"))
            .build();
    }
}