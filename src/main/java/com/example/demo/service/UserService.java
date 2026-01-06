package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User getCurrentUser() {
        return new User(
                1L,
                "test@mail.com",
                "Test",
                "User",
                "U123"
        );
    }
}
