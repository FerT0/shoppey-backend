package com.shoppey.backend.services;

import com.shoppey.backend.models.entity.UserEntity;

import java.util.List;

public interface IUser {

    UserEntity create(UserEntity userEntity);

    UserEntity findById(Long id);

    List<UserEntity> getAllUsers();

    void deleteUser(UserEntity userEntity);

}
