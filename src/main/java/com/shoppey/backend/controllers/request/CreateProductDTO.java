package com.shoppey.backend.controllers.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductDTO {
    @NotBlank
    private String productName;

    @NotBlank
    private String productDescription;

    @NotNull
    private double productPrice;

    @NotBlank
    private String productPicture;

    @NotBlank
    private String category;

    @NotNull
    private Long postedByUserId;
}
