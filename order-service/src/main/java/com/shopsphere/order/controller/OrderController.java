package com.shopsphere.order.controller;

import com.shopsphere.order.entity.Order;
import com.shopsphere.order.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController // Converts this class into a REST controller.
@RequestMapping("/orders") // Base URL for this service's APIs.
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        // Constructor injection supplies the service dependency.
        this.service=service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order create(@Valid @RequestBody Order order){
        // @Valid executes Bean Validation annotations before business logic.
        return service.create(order);
    }

    @GetMapping
    public List<Order> all(){ return service.all(); }

    @GetMapping("/{id}")
    public Order byId(@PathVariable Long id){ return service.byId(id); }

}
