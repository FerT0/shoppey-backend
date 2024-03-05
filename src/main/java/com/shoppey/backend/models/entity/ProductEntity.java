package com.shoppey.backend.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "product_price")
    private double productPrice;

    @Column(name = "product_picture")
    private String productPicture;

    @Column(name = "category")
    private String category;

    @Column(name = "created_at")
    private String created_at;

    @ManyToOne
    @JoinColumn(name = "posted_by_id")
    @JsonBackReference
    private UserEntity postedBy;
}
