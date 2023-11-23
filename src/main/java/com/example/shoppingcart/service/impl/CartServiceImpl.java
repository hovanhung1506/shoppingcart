package com.example.shoppingcart.service.impl;

import com.example.shoppingcart.domain.Cart;
import com.example.shoppingcart.domain.Product;
import com.example.shoppingcart.entity.CartEntity;
import com.example.shoppingcart.entity.OrderDetailsEntity;
import com.example.shoppingcart.entity.ProductEntity;
import com.example.shoppingcart.repository.ProductRepository;
import com.example.shoppingcart.service.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartEntity cartEntity;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Cart> getAll() {
        List<OrderDetailsEntity> orderDetailsEntities = cartEntity.getAll();
        List<Cart> list = new ArrayList<>();
        orderDetailsEntities.forEach(details -> {
            Product product = modelMapper.map(details.getProductEntity(), Product.class);
            Cart cart = new Cart();
            cart.setProduct(product);
            cart.setQuantity(details.getQuantity());
            list.add(cart);
        });
        return list;
    }

    @Override
    public void addItem(Product product) {
        ProductEntity productEntity = productRepository.findById(product.getId()).orElseThrow();
        cartEntity.addItem(productEntity);
    }
}
