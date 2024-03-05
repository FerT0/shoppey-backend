package com.shoppey.backend.controllers.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDTO {
    private String username;
    private String password;
    private String name;
    private String last_name;
    private String email;
}
