package com.maveric.authenticationauthorizationservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JWTResponse {

    private String jwtToken;
    private UserResponse userWithOutPassword;

}
