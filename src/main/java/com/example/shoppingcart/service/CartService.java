package com.example.shoppingcart.service;

import com.example.shoppingcart.domain.Cart;
import com.example.shoppingcart.domain.Product;

import java.util.List;

public interface CartService {

    List<Cart> getAll();

    void addItem(Product product);
}
