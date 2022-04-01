package com.maveric.authenticationauthorizationservice.feign;

import com.maveric.authenticationauthorizationservice.model.UserResponse;
import com.maveric.authenticationauthorizationservice.model.Userdto;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(name = "user-service")
public interface FeignUser {
    @LoadBalanced
    @PostMapping("/api/v1/users")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody Userdto user);
    @GetMapping("/api/v1/users/getUserByEmail/{emailId}")
    public ResponseEntity<UserResponse> getUserDetailsByEmail(@PathVariable("emailId") String emailId);

}
