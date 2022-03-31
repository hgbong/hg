package com.example.hg.controller;

import com.example.hg.model.user.*;
import com.example.hg.model.usergroup.UserGroupAddRequestDto;
import com.example.hg.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UsersResponseDto> listUsers(@ModelAttribute UserSearchCriteria searchCriteria, Pageable pageable) {
        return userService.listUsers(searchCriteria, pageable);
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

    @PostMapping("/{userId}/groups")
    @Deprecated(since = "not implemented spec yet (TODO)")
    public UserResponseDto addGroupToUser(@PathVariable Long userId, @RequestBody UserGroupAddRequestDto request) {
        request.setUserId(userId);
        return userService.addGroupToUser(request);
    }

}
