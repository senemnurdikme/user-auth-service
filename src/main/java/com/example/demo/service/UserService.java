package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getCurrentUser() {
        // Şimdilik "current user" = DB’deki test@mail.com kullanıcı
        return userRepository.findByEmail("test@mail.com")
                .orElseThrow(() -> new RuntimeException("USER_NOT_FOUND"));
    }
}
