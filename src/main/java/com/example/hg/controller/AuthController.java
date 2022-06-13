package com.example.hg.controller;

import com.example.hg.model.user.UserJoinRequestDto;
import com.example.hg.model.user.UserResponseDto;
import com.example.hg.service.AuthService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /* view controller */

    @GetMapping("/join")
    public String joinView() {
        return "join";
    }

    /* rest api controller */

    @ApiOperation(value = "신규 회원가입")
    @PostMapping("/join")
    @ResponseBody
    public UserResponseDto join(UserJoinRequestDto  requestDto) {
        return authService.join(requestDto);
    }


}
