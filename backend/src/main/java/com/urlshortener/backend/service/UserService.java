package com.urlshortener.backend.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import com.urlshortener.backend.dtos.LoginRequest;
import com.urlshortener.backend.models.User;
import com.urlshortener.backend.repository.UserRepository;
import com.urlshortener.backend.security.jwt.JwtAuthenticationResponse;
import com.urlshortener.backend.security.jwt.JwtUtils;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;

    public User registerUser(User user) {
        // Hash the password before saving
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        
        // Save the user to the repository
        return userRepository.save(user);
    }


    public JwtAuthenticationResponse authenticateUser(LoginRequest loginRequest){

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
            )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = jwtUtils.generateToken(userDetails);
        return new JwtAuthenticationResponse(jwt);
    }


    public User findByUsername(String name) {
        return userRepository.findByUsername(name)
        .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + name));
    }
}
