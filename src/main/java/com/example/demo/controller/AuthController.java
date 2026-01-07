package com.example.demo.controller;

import com.example.demo.dto.AuthResponse;
import com.example.demo.model.User;
import com.example.demo.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody User user) {
        User savedUser = authService.register(user);

        return new AuthResponse(
                savedUser.getEmail(),  // username
                user.getPassword()     // plain password (requested to be shown)
        );
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody User user) {
        boolean success = authService.login(user.getEmail(), user.getPassword());

        if (!success) {
            throw new RuntimeException("Invalid credentials");
        }

        return new AuthResponse(
                user.getEmail(),       // username
                user.getPassword()      // plain password (requested to be shown)
        );
    }
}
