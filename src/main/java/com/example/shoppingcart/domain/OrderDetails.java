package com.example.shoppingcart.domain;

import lombok.Data;

@Data
public class OrderDetails {
    private Long id;
    private Long orderId;
    private Long productId;
    private int quantity;
}
