package com.shopsphere.order.service;

import com.shopsphere.order.entity.Order;
import com.shopsphere.order.repository.OrderRepository;
import org.springframework.stereotype.Service;
import com.shopsphere.order.client.ProductClient;
import java.util.List;

@Service // Marks this class as the business/service layer managed by Spring.
public class OrderService {
    private final OrderRepository repository;
    private final ProductClient productClient;

    public OrderService(OrderRepository repository, ProductClient productClient) {
        // Constructor injection: Spring supplies both dependencies.
        this.repository=repository;
        this.productClient=productClient;
    }

    public Order create(Order order){
        // ProductClient calls Product Service through OpenFeign.
        ProductClient.ProductResponse product=productClient.getProduct(order.getProductId());

        // Simple business rule: do not allow an order larger than available stock.
        if(product.getStock() < order.getQuantity()){
            throw new RuntimeException("Not enough product stock");
        }

        // Normal Java arithmetic calculates the order total.
        order.setTotalPrice(product.getPrice()*order.getQuantity());

        // save() stores the final order in this service's database.
        return repository.save(order);
    }

    public List<Order> all(){
        // findAll() comes from JpaRepository.
        return repository.findAll();
    }

    public Order byId(Long id){
        // Optional + orElseThrow() handles a missing order simply.
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }

}
