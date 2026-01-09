package com.example.demo.controller;

import com.example.demo.dto.UpdateUserRequest;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/by-email")
    public User byEmail(@RequestParam String email) {
        return userService.getByEmail(email);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody UpdateUserRequest req) {
        return userService.updateUser(id, req);
    }
}
