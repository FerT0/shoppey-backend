package com.shoppey.backend.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Range;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;

    @NotBlank
    private String product_name;

    @NotBlank
    private String product_description;

    @NotNull(message= "price may not be empty")
    @Range(min = 1)
    private int product_price;

    @NotBlank
    private String product_picture;

    @NotNull(message= "posted_by may not be empty")
    @Range(min = 1)
    @Range(min = 1)
    private Long posted_by;

    @NotBlank
    private String category;

    @NotBlank
    private String created_at;
}
