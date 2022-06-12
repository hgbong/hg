package com.example.hg.controller;

import com.example.hg.model.user.UserJoinRequestDto;
import com.example.hg.model.user.UserResponseDto;
import com.example.hg.service.AuthService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @ApiOperation(value = "신규 회원가입")
    @PostMapping("/join")
    public UserResponseDto join(UserJoinRequestDto requestDto) {
        return authService.join(requestDto);
    }
}
