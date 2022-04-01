package com.maveric.authenticationauthorizationservice.controller;


import com.maveric.authenticationauthorizationservice.feign.FeignUser;
import com.maveric.authenticationauthorizationservice.model.*;
import com.maveric.authenticationauthorizationservice.repo.AuthorisationRepo;
import com.maveric.authenticationauthorizationservice.service.UserService;
import com.maveric.authenticationauthorizationservice.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@CrossOrigin(value="*")
@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private UserService userService;

    @Autowired
    FeignUser feignUser;

    @Autowired
    AuthorisationRepo authorisationRepo;

    @CrossOrigin(value = "*")
    @PostMapping("/login")
    public ResponseEntity<JWTResponse> login(@RequestBody LoginRequest loginRequest) throws Exception {
        try {
             authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                     loginRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            throw new Exception(ConstFile.errorCode, ex);
        }

        final UserDetails userDetails = userService.loadUserByUsername(loginRequest.getEmail());
        final String token = jwtUtility.generateToken(userDetails);

        UserResponse userResponse=feignUser.getUserDetailsByEmail(loginRequest.getEmail()).getBody();
        return ResponseEntity.status(HttpStatus.OK).body(new JWTResponse(token,userResponse ));
    }

   // @CrossOrigin(value = "*")
    @PostMapping("/signup")
    public ResponseEntity<JWTResponse> signup(@RequestBody Userdto userDto) throws Exception {
        JWTRequest jwtRequest = new JWTRequest();
        jwtRequest.setEmail(userDto.getEmail());
        jwtRequest.setPassword(userDto.getPassword());

        authorisationRepo.save(jwtRequest);
       UserResponse userWithOutPassword = feignUser.createUser(userDto).getBody();
        final UserDetails userDetails = new User(userDto.getEmail(),userDto.getPassword(), new ArrayList<>());
        final String token = jwtUtility.generateToken(userDetails);
        return ResponseEntity.status(HttpStatus.OK).body(new JWTResponse(token,userWithOutPassword));
    }
}
