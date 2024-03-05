package com.shoppey.backend.services.impl;


import com.shoppey.backend.models.entity.UserEntity;
import com.shoppey.backend.repositories.UserRepository;
import com.shoppey.backend.services.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsersImpl implements IUser {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public UserEntity create(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Transactional
    @Override
    public UserEntity findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public List<UserEntity> getAllUsers() {
        return (List<UserEntity>) userRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteUser(UserEntity userEntity){
        userRepository.delete(userEntity);
    }
}
