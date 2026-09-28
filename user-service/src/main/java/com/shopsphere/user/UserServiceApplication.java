package com.shopsphere.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication // Spring Boot auto-configuration and component scanning.
@EnableDiscoveryClient // Registers this microservice with Eureka.

public class UserServiceApplication {
    public static void main(String[] args) {
        // Starts the Spring Boot application.
        SpringApplication.run(UserServiceApplication.class,args);
    }
}
