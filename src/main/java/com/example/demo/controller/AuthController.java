package com.example.demo.controller;

import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.model.User;
import com.example.demo.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody User user) {
        User saved = authService.register(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new AuthResponse(saved.getEmail(), "CREATED"));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        boolean success = authService.login(request.getEmail(), request.getPassword());

        if (!success) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthResponse(request.getEmail(), "INVALID"));
        }

        return ResponseEntity.ok(new AuthResponse(request.getEmail(), "SUCCESS"));
    }
}
