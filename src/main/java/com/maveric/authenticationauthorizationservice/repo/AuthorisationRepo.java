package com.maveric.authenticationauthorizationservice.repo;


import com.maveric.authenticationauthorizationservice.model.JWTRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorisationRepo extends JpaRepository<JWTRequest,Integer> {

    JWTRequest findByEmail(String email);
}
