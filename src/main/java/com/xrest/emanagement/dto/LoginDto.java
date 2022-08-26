package com.xrest.emanagement.dto;

import lombok.Data;

@Data
public class LoginDto {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String role;
}
