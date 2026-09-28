package com.shopsphere.product.controller;

import com.shopsphere.product.entity.Product;
import com.shopsphere.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController // Converts this class into a REST controller.
@RequestMapping("/products") // Base URL for this service's APIs.
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        // Constructor injection supplies the service dependency.
        this.service=service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@Valid @RequestBody Product p){
        // @RequestBody maps incoming JSON; @Valid runs validation.
        return service.create(p);
    }

    @GetMapping
    public List<Product> all(){ return service.all(); }

    @GetMapping("/{id}")
    public Product byId(@PathVariable Long id){ return service.byId(id); }

}
