package com.shoppey.backend.services;

import com.shoppey.backend.models.entity.ProductEntity;

import java.util.List;

public interface IProduct {

    ProductEntity create(ProductEntity productEntity);

    ProductEntity findById(Long id);

    List<ProductEntity> getAllProducts();

    void deleteProduct(ProductEntity productEntity);
}
