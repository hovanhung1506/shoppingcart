package com.example.shoppingcart.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
@Scope(scopeName = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartEntity {
    private OrderEntity orderEntity = new OrderEntity();

    public void addItem(ProductEntity productEntity) {
        boolean isFound = Optional.of(orderEntity.getOrderDetailsEntities())
                .orElse(new ArrayList<>())
                .stream()
                .anyMatch(product -> Objects.equals(product.getProductEntity().getId(), productEntity.getId()));

        if (isFound) {
            orderEntity.getOrderDetailsEntities().forEach(detail -> {
                if (Objects.equals(detail.getProductEntity().getId(), productEntity.getId())) {
                    detail.setQuantity(detail.getQuantity() + 1);
                }
            });
        } else {
            OrderDetailsEntity detail = new OrderDetailsEntity();
            detail.setQuantity(1);
            detail.setProductEntity(productEntity);
            orderEntity.getOrderDetailsEntities().add(detail);
        }
    }

    public List<OrderDetailsEntity> getAll() {
        return orderEntity.getOrderDetailsEntities();
    }

    public int count() {
        return orderEntity.getOrderDetailsEntities()
                .stream()
                .map(OrderDetailsEntity::getQuantity)
                .reduce(0, Integer::sum);
    }

    public void emptyCart() {
        orderEntity.setOrderDetailsEntities(List.of());
    }

    public void removeItem(ProductEntity productEntity) {
        Iterator<OrderDetailsEntity> iterator = orderEntity.getOrderDetailsEntities().iterator();
        while (iterator.hasNext()) {
            OrderDetailsEntity detail = iterator.next();
            if (Objects.equals(detail.getProductEntity().getId(), productEntity.getId())) {
                if (detail.getQuantity() > 1) {
                    detail.setQuantity(detail.getQuantity() - 1);
                } else {
                    iterator.remove();
                }
            }
        }
    }

}
