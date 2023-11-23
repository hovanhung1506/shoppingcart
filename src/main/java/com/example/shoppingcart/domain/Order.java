package com.example.shoppingcart.domain;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Order {
    private Long id;
    private LocalDate orderDate;
    private String customerName;
    private String customerAddress;
}
