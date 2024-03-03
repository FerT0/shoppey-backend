package com.shoppey.backend.repositories;

import com.shoppey.backend.models.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername (String username);
    Optional<UserEntity> findByEmail (String email);


}
