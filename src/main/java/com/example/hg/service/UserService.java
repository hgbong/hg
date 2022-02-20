package com.example.hg.service;

import com.example.hg.model.user.User;
import com.example.hg.model.user.UserCreateRequestDto;
import com.example.hg.model.user.UserResponseDto;
import com.example.hg.model.user.UsersResponseDto;
import com.example.hg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<UsersResponseDto> listUsers() {
        List<UsersResponseDto> result = new ArrayList<>();

        Iterable<User> users = userRepository.findAll();
        users.forEach(u -> {
            result.add(UsersResponseDto.builder()
            .userId(u.getUserId()).userName(u.getUserName()).build());
        });

        return result;
    }


    public UserResponseDto detailUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User u = user.get();
            return UserResponseDto.builder()
                    .userId(u.getUserId())
                    .userName(u.getUserName())
                    .build();
        } else {
            // TODO: exception 404
            return null;
        }
    }

    public UserResponseDto createUser(UserCreateRequestDto request) {
        String userName = request.getUserName();

        // TODO: validation


        User u = userRepository.save(User.builder().userName(request.getUserName()).build());

        // TODO: log

        return UserResponseDto.builder()
                .userId(u.getUserId())
                .userName(u.getUserName())
                .build();
    }

}
