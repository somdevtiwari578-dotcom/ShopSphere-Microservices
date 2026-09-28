package com.shopsphere.product.service;

import com.shopsphere.product.entity.Product;
import com.shopsphere.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service // Marks this class as the business/service layer managed by Spring.
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        // Constructor injection: Spring automatically supplies the repository object.
        this.repository=repository;
    }

    public Product create(Product p){
        // save() is a Spring Data JPA built-in repository method.
        return repository.save(p);
    }
    public List<Product> all(){
        // findAll() fetches all products from the database.
        return repository.findAll();
    }
    public Product byId(Long id){
        // findById() returns Optional<Product>; orElseThrow() returns the object or throws.
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

}
