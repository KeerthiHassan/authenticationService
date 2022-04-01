package com.maveric.authenticationauthorizationservice.model;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}

