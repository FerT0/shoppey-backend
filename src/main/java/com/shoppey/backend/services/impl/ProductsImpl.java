package com.shoppey.backend.services.impl;

import com.shoppey.backend.models.entity.ProductEntity;
import com.shoppey.backend.repositories.ProductRepository;
import com.shoppey.backend.services.IProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductsImpl implements IProduct {
    @Autowired
    private ProductRepository productRepository;

    @Transactional
    @Override
    public ProductEntity create(ProductEntity productEntity) {
        return productRepository.save(productEntity);
    }

    @Transactional
    @Override
    public ProductEntity findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public List<ProductEntity> getAllProducts() {
        return (List<ProductEntity>) productRepository.findAll();
    }

    @Override
    public void deleteProduct(ProductEntity productEntity) {
        productRepository.delete(productEntity);
    }
}
