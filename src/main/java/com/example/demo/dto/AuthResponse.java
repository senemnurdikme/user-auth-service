package com.example.demo.dto;

public class AuthResponse {

    private String username;
    private String password;

    public AuthResponse(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
