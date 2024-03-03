package com.shoppey.backend.controllers;

import com.shoppey.backend.controllers.request.RegisterUserDTO;
import com.shoppey.backend.models.entity.UserEntity;
import com.shoppey.backend.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("registerUser")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterUserDTO registerUserDTO){
        LocalDate today = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        UserEntity userEntity = UserEntity.builder()
                .username(registerUserDTO.getUsername())
                .password(registerUserDTO.getPassword())
                .email(registerUserDTO.getEmail())
                .created_at(today.toString() + " " + currentTime.toString().substring(0, 8))
                .build();

        userRepository.save(userEntity);
        return ResponseEntity.ok(userEntity);
    }


}
