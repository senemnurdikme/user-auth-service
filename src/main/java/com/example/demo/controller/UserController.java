package com.example.demo.controller;

import com.example.demo.dto.PatchUserRequest;
import com.example.demo.dto.UpdateUserRequest;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/by-email")
    public User getByEmail(@RequestParam String email) {
        return userService.getByEmail(email);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id,
                           @RequestBody UpdateUserRequest req) {
        return userService.updateUser(id, req);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<User> patchUser(@PathVariable Long id,
                                          @RequestBody PatchUserRequest request) {

        User updated = userService.patchUser(id, request);
        return ResponseEntity.ok(updated);
    }
}
