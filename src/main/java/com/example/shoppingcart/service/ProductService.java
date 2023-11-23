package com.example.shoppingcart.service;

import com.example.shoppingcart.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProduct();
    void create(Product product);
}
