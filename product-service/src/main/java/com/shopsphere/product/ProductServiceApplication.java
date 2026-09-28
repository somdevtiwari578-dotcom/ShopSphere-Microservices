package com.shopsphere.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication // Spring Boot auto-configuration and component scanning.
@EnableDiscoveryClient // Registers this microservice with Eureka.

public class ProductServiceApplication {
    public static void main(String[] args) {
        // Starts the Spring Boot application.
        SpringApplication.run(ProductServiceApplication.class,args);
    }
}
