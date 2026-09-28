package com.shopsphere.product.repository;

import com.shopsphere.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
    // JpaRepository already supplies save(), findAll(), findById(), deleteById(), etc.
    // These methods come from the Spring Data JPA library; no SQL is required for basic CRUD.
}
