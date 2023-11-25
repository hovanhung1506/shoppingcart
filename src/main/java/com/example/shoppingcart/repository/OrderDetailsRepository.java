package com.example.shoppingcart.repository;

import com.example.shoppingcart.entity.OrderDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRepository extends JpaRepository<OrderDetailsEntity, Long> {
}
