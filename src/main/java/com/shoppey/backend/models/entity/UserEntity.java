package com.shoppey.backend.models.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import jakarta.validation.constraints.Email;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String username;

    private String password;

    private String name;
    private String last_name;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    @OneToMany(targetEntity = ProductEntity.class, fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "postedBy")
    @JsonManagedReference
    private List<ProductEntity> postedProducts;
}
