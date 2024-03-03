package com.shoppey.backend.controllers.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductDTO {
    private String product_name;
    private String product_description;
    private int product_price;
    private String product_picture;
    private Long posted_by;
    private String category;
}
