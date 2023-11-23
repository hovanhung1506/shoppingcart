package com.example.shoppingcart.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cart {
    private Product product;
    private int quantity;
}
