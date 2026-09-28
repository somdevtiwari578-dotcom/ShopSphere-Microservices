package com.shopsphere.order.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "orders")// JPA maps this Java class to a database table.
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull private Long userId;
    @NotNull private Long productId;
    @Min(1) private int quantity;
    private double totalPrice;

    public Order() { /* JPA requires a no-argument constructor. */ }

    public Long getId(){return id;}
    public Long getUserId(){return userId;}
    public void setUserId(Long userId){this.userId=userId;}
    public Long getProductId(){return productId;}
    public void setProductId(Long productId){this.productId=productId;}
    public int getQuantity(){return quantity;}
    public void setQuantity(int quantity){this.quantity=quantity;}
    public double getTotalPrice(){return totalPrice;}
    public void setTotalPrice(double totalPrice){this.totalPrice=totalPrice;}

}
