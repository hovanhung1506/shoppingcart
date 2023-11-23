package com.example.shoppingcart.service.impl;

import com.example.shoppingcart.domain.Product;
import com.example.shoppingcart.entity.ProductEntity;
import com.example.shoppingcart.repository.ProductRepository;
import com.example.shoppingcart.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Product> getAllProduct() {
        List<ProductEntity> productEntities = productRepository.findAll();
        return productEntities.stream().map(product -> modelMapper.map(product, Product.class)).collect(Collectors.toList());
    }

    @Override
    public void create(Product product) {
        ProductEntity entity = modelMapper.map(product, ProductEntity.class);
        productRepository.save(entity);
    }
}
