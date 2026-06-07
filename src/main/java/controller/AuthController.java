package com.library.backend.controller;

import com.library.backend.model.User;
import com.library.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public String login(@RequestBody User loginRequest) {
        User existingUser = userRepository.findByEmail(loginRequest.getEmail());

        if (existingUser != null && existingUser.getPassword().equals(loginRequest.getPassword())) {
            // NEW: Instead of "SUCCESS", we send back their actual role! (e.g., "ADMIN" or "USER")
            return existingUser.getRole();
        } else {
            return "FAILED";
        }
    }
}