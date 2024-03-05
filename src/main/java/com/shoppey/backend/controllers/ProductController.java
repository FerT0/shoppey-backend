package com.shoppey.backend.controllers;

import com.shoppey.backend.controllers.request.CreateProductDTO;
import com.shoppey.backend.controllers.response.ResponseProductDTO;
import com.shoppey.backend.models.entity.ProductEntity;
import com.shoppey.backend.models.entity.UserEntity;
import com.shoppey.backend.repositories.ProductRepository;
import com.shoppey.backend.repositories.UserRepository;
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
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/createProduct")
    public ResponseEntity<ResponseProductDTO> createProduct (@Valid @RequestBody CreateProductDTO createProductDTO) {
        UserEntity postedBy = userRepository.findById(createProductDTO.getPostedByUserId())
                .orElseThrow(() -> new RuntimeException("Couldn't find user"));
        LocalDate today = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        String createdAt = today.toString() + " " + currentTime.toString().substring(0, 8);
        ProductEntity productEntity = ProductEntity.builder()
                .productName(createProductDTO.getProductName())
                .productDescription(createProductDTO.getProductDescription())
                .productPrice(createProductDTO.getProductPrice())
                .productPicture(createProductDTO.getProductPicture())
                .category(createProductDTO.getCategory())
                .postedBy(postedBy)
                .created_at(createdAt)
                .build();
        productRepository.save(productEntity);

        ResponseProductDTO responseProductDTO = new ResponseProductDTO(
                createProductDTO.getProductName(),
                createProductDTO.getProductDescription(),
                createProductDTO.getProductPrice(),
                createProductDTO.getCategory(),
                createdAt,
                createProductDTO.getPostedByUserId());
        return ResponseEntity.ok(responseProductDTO);
    }
}
