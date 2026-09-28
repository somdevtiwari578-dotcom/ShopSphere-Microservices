package com.shopsphere.order.repository;

import com.shopsphere.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
    // JpaRepository already supplies save(), findAll(), findById(), deleteById(), etc.
    // These methods come from the Spring Data JPA library; no SQL is required for basic CRUD.
}
