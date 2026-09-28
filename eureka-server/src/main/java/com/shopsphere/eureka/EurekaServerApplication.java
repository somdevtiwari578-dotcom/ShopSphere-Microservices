package com.shopsphere.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication // Enables Spring Boot auto-configuration and component scanning.
@EnableEurekaServer // Turns this application into a Eureka service-discovery server.
public class EurekaServerApplication {
    public static void main(String[] args) {
        // SpringApplication.run() starts the Spring Boot application.
        SpringApplication.run(EurekaServerApplication.class,args);
    }
}
