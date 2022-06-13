package com.example.hg.service;

import com.example.hg.model.security.Role;
import com.example.hg.model.user.User;
import com.example.hg.model.user.UserJoinRequestDto;
import com.example.hg.model.user.UserResponseDto;
import com.example.hg.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final UserRepository userRepository;

    public UserResponseDto join(UserJoinRequestDto requestDto) {

        Optional<User> xxx = userRepository.findByUserEmail(requestDto.getUserEmail());

        if (!xxx.isEmpty()) {
            throw new RuntimeException("a user with the same email already exists");
        }

        User user = User.builder().userName(requestDto.getUserName()).userEmail(requestDto.getUserEmail()).role(Role.USER).build();
        userRepository.save(user);

        return new UserResponseDto().convertUserResponseDto(user);
    }

//    private final UserRepository userRepository;


}
