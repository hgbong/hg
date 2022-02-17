package com.example.hg.controller;

import com.example.hg.model.user.UserCreateRequest;
import com.example.hg.model.user.UserResponse;
import com.example.hg.model.user.UsersResponse;
import com.example.hg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UsersResponse> listUsers() {
        return userService.listUsers();
    }

    @GetMapping("/{userId}")
    public UserResponse detailUser(@PathVariable Long userId) {
        return userService.detailUser(userId);
    }

    @PostMapping
    public UserResponse createUser(@RequestBody UserCreateRequest request) {
        return userService.createUser(request);
    }
}
