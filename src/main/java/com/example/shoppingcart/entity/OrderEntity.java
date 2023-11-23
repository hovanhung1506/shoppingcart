package com.example.shoppingcart.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name ="orders")
@Getter
@Setter
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate orderDate;
    private String customerName;
    private String customerAddress;

    @OneToMany(mappedBy = "orderEntity", fetch = FetchType.EAGER)
    List<OrderDetailsEntity> orderDetailsEntities;
}
