package com.example.hg.controller;

import com.example.hg.model.user.UserCreateRequestDto;
import com.example.hg.model.user.UserResponseDto;
import com.example.hg.model.user.UserUpdateRequestDto;
import com.example.hg.model.user.UsersResponseDto;
import com.example.hg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired // TODO final 필드로 정의 및 RequiredArgsConstructor로 참조
    private UserService userService;

    @GetMapping
    public List<UsersResponseDto> listUsers() {
        return userService.listUsers();
    }

    @GetMapping("/{userId}")
    public UserResponseDto detailUser(@PathVariable Long userId) {
        return userService.detailUser(userId);
    }

    @PostMapping
    public UserResponseDto createUser(@RequestBody UserCreateRequestDto request) {
        return userService.createUser(request);
    }

    @PutMapping("/{userId}")
    public UserResponseDto updateUser(@PathVariable Long userId, @RequestBody UserUpdateRequestDto request) {
        request.setUserId(userId);
        return userService.updateUser(request);
    }
}
