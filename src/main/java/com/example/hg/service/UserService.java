package com.example.hg.service;

import com.example.hg.model.group.Group;
import com.example.hg.model.user.*;
import com.example.hg.model.usergroup.UserGroup;
import com.example.hg.model.usergroup.UserGroupAddRequestDto;
import com.example.hg.repository.GroupRepository;
import com.example.hg.repository.UserGroupRepository;
import com.example.hg.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final GroupRepository groupRepository;

    private final UserGroupRepository userGroupRepository;

    @PostConstruct
    public void init() {
        userRepository.save(User.builder().userName("name1").userEmail("test1@gmail.com").build());
        userRepository.save(User.builder().userName("name2").userEmail("test2@gmail.com").build());
        userRepository.save(User.builder().userName("name3").userEmail("test3@gmail.com").build());
        userRepository.save(User.builder().userName("name4").userEmail("test4@gmail.com").build());
        userRepository.save(User.builder().userName("name5").userEmail("test5@gmail.com").build());
    }


    public List<UsersResponseDto> listUsers(UserSearchCriteria searchCriteria, Pageable pageable) {
        List<UsersResponseDto> result = new ArrayList<>();

        // FIXME 검색조건이 입력된 경우에만 쿼리 조건문 생성 (e.g. query param으로 userName=test 이 입력되었을 때 userName으로만 조건 검색)
        // List<User> xxx = userRepository.findByUserNameContainsAndUserEmailContains(searchCriteria.getUserName(), searchCriteria.getUserEmail(), pageable);

        Page<User> users = userRepository.findAll(pageable);
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
