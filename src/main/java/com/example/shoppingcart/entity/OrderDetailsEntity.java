package com.example.shoppingcart.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "orderDetails")
public class OrderDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;

    @ManyToOne
    @JoinColumn(name ="productID")
    private ProductEntity productEntity;

    @ManyToOne
    @JoinColumn(name = "orderID")
    private OrderEntity orderEntity;
}
