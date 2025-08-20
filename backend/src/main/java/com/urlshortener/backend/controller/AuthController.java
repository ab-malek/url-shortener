package com.urlshortener.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urlshortener.backend.dtos.LoginRequest;
import com.urlshortener.backend.dtos.RegisterRequest;
import com.urlshortener.backend.models.User;
import com.urlshortener.backend.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private UserService userServiece;


    @PostMapping("/public/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {

        return ResponseEntity.ok(userServiece.authenticateUser(loginRequest));
    }
    
    @PostMapping("/public/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerUser){
        User user = new User();
        user.setUsername(registerUser.getUsername());
        user.setEmail(registerUser.getEmail());
        user.setRole("ROLE_USER"); // Assuming roles are set to a default value
        user.setPassword(registerUser.getPassword()); // Password should be hashed in a real application
        
        userServiece.registerUser(user);
        return ResponseEntity.ok("User registered successfully");
    }
}
