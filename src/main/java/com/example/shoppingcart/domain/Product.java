package com.example.shoppingcart.domain;

import lombok.Data;

@Data
public class Product {
    private Long id;
    private String productName;
    private String productDescription;
    private double unitPrice;
}
