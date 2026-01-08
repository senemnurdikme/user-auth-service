package com.example.demo.dto;

public class AuthResponse {
    private String email;
    private String status;

    public AuthResponse(String email, String status) {
        this.email = email;
        this.status = status;
    }

    public String getEmail() { return email; }
    public String getStatus() { return status; }
}
