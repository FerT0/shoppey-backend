package com.shoppey.backend.controllers;

import com.shoppey.backend.controllers.request.CreateProductDTO;
import com.shoppey.backend.models.entity.ProductEntity;
import com.shoppey.backend.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/createProduct")
    public ResponseEntity<?> createProduct (@Valid @RequestBody CreateProductDTO createProductDTO) {
        LocalDate today = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        ProductEntity productEntity = ProductEntity.builder()
                .product_name(createProductDTO.getProduct_name())
                .product_description(createProductDTO.getProduct_description())
                .product_price(createProductDTO.getProduct_price())
                .product_picture(createProductDTO.getProduct_picture())
                .category(createProductDTO.getCategory())
                .posted_by(createProductDTO.getPosted_by())
                .created_at(today.toString() + " " + currentTime.toString().substring(0, 8))
                .build();
        productRepository.save(productEntity);
        return ResponseEntity.ok(productEntity);
    }
}
