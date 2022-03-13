package com.example.hg.service;

import com.example.hg.model.group.Group;
import com.example.hg.model.user.*;
import com.example.hg.model.usergroup.UserGroup;
import com.example.hg.model.usergroup.UserGroupAddRequestDto;
import com.example.hg.repository.GroupRepository;
import com.example.hg.repository.UserGroupRepository;
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

    @Autowired
    private UserGroupRepository userGroupRepository;


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
        // TODO exception 생성 및 global exception handler 처리
        User u = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));
        return new UserResponseDto().convertUserResponseDto(u);
    }

    public UserResponseDto createUser(UserCreateRequestDto request) {
        String userName = request.getUserName();
        // TODO: validation


        User u = userRepository.save(User.builder().userName(request.getUserName()).build());

        // TODO: log

        return new UserResponseDto().convertUserResponseDto(u);
    }

    public UserResponseDto updateUser(UserUpdateRequestDto request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("user not found"));
        Group group = groupRepository.findById(request.getGroupId()).orElseThrow(() -> new RuntimeException("group not found"));

        // TODO 그외 사용자 업데이트 시 업데이트할 정보들 있으면 추가

        userGroupRepository.save(UserGroup.builder().user(user).group(group).build());
        return new UserResponseDto().convertUserResponseDto(user);
    }

    public UserResponseDto addGroupToUser(UserGroupAddRequestDto request) {
        return null;
    }
}
