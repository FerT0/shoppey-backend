package com.shoppey.backend.controllers;

import com.shoppey.backend.controllers.exceptions.UserNotFoundException;
import com.shoppey.backend.controllers.request.CreateUserDTO;
import com.shoppey.backend.controllers.response.ResponseUserDTO;
import com.shoppey.backend.models.entity.UserEntity;
import com.shoppey.backend.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsers(){
        List<UserEntity> users = (List<UserEntity>) userRepository.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id){
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping("createUser")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDTO createUserDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            LocalDateTime createdAt = LocalDateTime.now();

            UserEntity userEntity = UserEntity.builder()
                    .name(createUserDTO.getName())
                    .last_name(createUserDTO.getLast_name())
                    .username(createUserDTO.getUsername())
                    .password(createUserDTO.getPassword())
                    .email(createUserDTO.getEmail())
                    .created_at(createdAt)
                    .build();

            userRepository.save(userEntity);

            ResponseUserDTO responseUserDTO = new ResponseUserDTO(
                    createUserDTO.getName(),
                    createUserDTO.getLast_name(),
                    createUserDTO.getUsername(),
                    createUserDTO.getEmail());

            return ResponseEntity.status(HttpStatus.OK).body(responseUserDTO);
        } catch (DataIntegrityViolationException e) {
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping("deleteUser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        UserEntity userToDelete = userRepository.findById(id).
                orElseThrow(() -> new UserNotFoundException(id));
        userRepository.delete(userToDelete);
        response.put("message", "successfully removed user with ID " + userToDelete.getId());
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
}
