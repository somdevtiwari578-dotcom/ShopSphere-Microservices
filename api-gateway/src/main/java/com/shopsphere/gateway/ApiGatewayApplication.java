package com.shopsphere.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Enables Spring Boot configuration and component scanning.
public class ApiGatewayApplication {
    public static void main(String[] args) {
        // Starts the Gateway application.
        SpringApplication.run(ApiGatewayApplication.class,args);
    }
}
