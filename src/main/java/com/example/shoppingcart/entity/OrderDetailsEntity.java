package com.example.shoppingcart.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orderDetails")
@Getter
@Setter
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

    @Override
    public String toString() {
        return "OrderDetailsEntity{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", productEntity=" + productEntity +
                ", orderEntity=" + orderEntity +
                '}';
    }
}
