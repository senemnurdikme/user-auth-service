package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public boolean login(String email, String password) {
        return "test@mail.com".equals(email)
                && "123456".equals(password);
    }
}
