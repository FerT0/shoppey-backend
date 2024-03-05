package com.shoppey.backend.controllers.response;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.shoppey.backend.models.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProductDTO {
    private String productName;
    private String productDescription;
    private double productPrice;
    private String category;
    private String created_at;
    private Long postedBy;
}
