package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest req) {
        boolean ok = authService.login(req.getEmail(), req.getPassword());
        if (ok) {
            return ResponseEntity.ok("LOGIN_OK");
        }
        return ResponseEntity.status(401).body("LOGIN_FAIL");
    }
}
