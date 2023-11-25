package com.example.shoppingcart.service.impl;

import com.example.shoppingcart.entity.CartEntity;
import com.example.shoppingcart.entity.OrderDetailsEntity;
import com.example.shoppingcart.entity.OrderEntity;
import com.example.shoppingcart.repository.OrderDetailsRepository;
import com.example.shoppingcart.repository.OrderRepository;
import com.example.shoppingcart.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CheckoutImpl implements CheckoutService {

    @Autowired
    private CartEntity cartEntity;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;


    @Override
    public void checkout(String name, String address) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCustomerName(name);
        orderEntity.setCustomerAddress(address);
        orderEntity.setOrderDate(LocalDate.now());
        orderEntity = orderRepository.save(orderEntity);
        for(OrderDetailsEntity detail : cartEntity.getAll()) {
            detail.setOrderEntity(orderEntity);
        }
        orderDetailsRepository.saveAll(cartEntity.getAll());
    }
}
