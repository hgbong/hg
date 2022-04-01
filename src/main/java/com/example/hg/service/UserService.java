package com.example.hg.service;

import com.example.hg.model.group.Group;
import com.example.hg.model.user.*;
import com.example.hg.model.usergroup.UserGroup;
import com.example.hg.model.usergroup.UserGroupAddRequestDto;
import com.example.hg.repository.GroupRepository;
import com.example.hg.repository.UserGroupRepository;
import com.example.hg.repository.UserRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final GroupRepository groupRepository;

    private final UserGroupRepository userGroupRepository;

    private final JPAQueryFactory jpaQueryFactory;

    @PostConstruct
    public void init() {
        userRepository.save(User.builder().userName("name1").userEmail("test1@gmail.com").build());
        userRepository.save(User.builder().userName("name2").userEmail("test2@gmail.com").build());
        userRepository.save(User.builder().userName("name3").userEmail("test3@gmail.com").build());
        userRepository.save(User.builder().userName("name4").userEmail("test4@gmail.com").build());
        userRepository.save(User.builder().userName("name5").userEmail("test5@gmail.com").build());
    }


    public List<UsersResponseDto> listUsers(UserSearchCriteria searchCriteria, Pageable pageable) {
        QUser u = new QUser("u");

        // TODO 동적으로 search criteria 체크할 수는 없을지
        BooleanBuilder builder = new BooleanBuilder();
        if (StringUtils.isNotBlank(searchCriteria.getUserName())) {
            builder.and(u.userName.contains(searchCriteria.getUserName()));
        }

        if (StringUtils.isNotBlank(searchCriteria.getUserEmail())) {
            builder.and(u.userEmail.contains(searchCriteria.getUserEmail()));
        }

        // TODO pageable로부터 querydsl 정렬 객체 OrderSpecifier 생성
        // CONSIDER pageable 대신 page, size, sort 객체를 별도로 받을지
        List<OrderSpecifier> orderSpecifiers = new ArrayList<>();
        pageable.getSort().forEach(sort -> {
            Order order = sort.getDirection().equals(Sort.Direction.ASC) ? Order.ASC : Order.DESC;
            Expression expression = Expressions.path(User.class, sort.getProperty()); // sort.getProperty expects userName, userEmail
            OrderSpecifier orderSpecifier = new OrderSpecifier(order, expression);
            orderSpecifiers.add(orderSpecifier);
        });

        List<User> users = jpaQueryFactory
                .selectFrom(u)
                .where(builder)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .orderBy(orderSpecifiers.toArray(OrderSpecifier[]::new))
                .fetch();

        List<UsersResponseDto> result = new ArrayList<>();
        return users.stream().map(user ->
                UsersResponseDto.builder()
                        .userId(user.getUserId())
                        .userName(user.getUserName())
                        .userEmail(user.getUserEmail())
                        .build())
                .collect(Collectors.toList());
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
