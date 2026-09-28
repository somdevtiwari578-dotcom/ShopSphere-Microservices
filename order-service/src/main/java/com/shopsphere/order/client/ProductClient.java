package com.shopsphere.order.client;

import com.shopsphere.order.config.FeignAuthInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="product-service", configuration = FeignAuthInterceptor.class)
// @FeignClient creates an HTTP client automatically.
// Eureka is used to find the service by the name "product-service".
public interface ProductClient {

    @GetMapping("/products/{id}")
    ProductResponse getProduct(@PathVariable Long id);
    // Feign generates the HTTP implementation for this method.

    class ProductResponse {
        private Long id;
        private String name;
        private double price;
        private int stock;

        public Long getId(){return id;}
        public String getName(){return name;}
        public double getPrice(){return price;}
        public int getStock(){return stock;}
    }
}
