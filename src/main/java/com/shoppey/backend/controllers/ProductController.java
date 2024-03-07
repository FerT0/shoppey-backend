package com.shoppey.backend.controllers;

import com.shoppey.backend.controllers.exceptions.ProductNotFoundException;
import com.shoppey.backend.controllers.exceptions.UserNotFoundException;
import com.shoppey.backend.controllers.request.CreateProductDTO;
import com.shoppey.backend.controllers.response.ResponseProductDTO;
import com.shoppey.backend.models.entity.ProductEntity;
import com.shoppey.backend.models.entity.UserEntity;
import com.shoppey.backend.repositories.ProductRepository;
import com.shoppey.backend.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<?> getAllProducts(){
        List<ProductEntity> products = (List<ProductEntity>) productRepository.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id){
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PostMapping("/createProduct")
    public ResponseEntity<?> createProduct (@Valid @RequestBody CreateProductDTO createProductDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            UserEntity postedBy = userRepository.findById(createProductDTO.getPostedByUserId())
                    .orElseThrow(() -> new UserNotFoundException(createProductDTO.getPostedByUserId()));
            LocalDateTime createdAt = LocalDateTime.now();
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
        } catch (DataIntegrityViolationException e) {
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping("deleteProduct/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        ProductEntity productToDelete = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        productRepository.delete(productToDelete);
        response.put("message", "successfully removed product with ID " + productToDelete.getId());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
