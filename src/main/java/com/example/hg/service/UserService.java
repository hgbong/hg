package com.example.hg.service;

import com.example.hg.model.group.Group;
import com.example.hg.model.user.*;
import com.example.hg.repository.GroupRepository;
import com.example.hg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;


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
        // TODO 404
        User u = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));
        return UserResponseDto.convertUserResponseDto(u);
    }

    public UserResponseDto createUser(UserCreateRequestDto request) {
        String userName = request.getUserName();
        // TODO: validation


        User u = userRepository.save(User.builder().userName(request.getUserName()).build());

        // TODO: log

        return UserResponseDto.convertUserResponseDto(u);
    }

    public UserResponseDto updateUser(UserUpdateRequestDto request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("user not found"));
        Group group = groupRepository.findById(request.getGroupId()).orElseThrow(() -> new RuntimeException("group not found"));

        user.setGroup(group);

        return UserResponseDto.convertUserResponseDto(user);
    }
}
