package com.shoppey.backend.controllers.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProductDTO {
    private String productName;
    private String productDescription;
    private double productPrice;
    private String category;
    private LocalDateTime created_at;
    private Long postedBy;
}
