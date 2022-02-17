package com.example.hg.service;

import com.example.hg.model.user.UserCreateRequest;
import com.example.hg.model.user.UserResponse;
import com.example.hg.model.user.UsersResponse;
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


    public List<UsersResponse> listUsers() {
        List<UsersResponse> result = new ArrayList<>();

        Iterable<UserResponse> users = userRepository.findAll();
        for (UserResponse u : users) {
            result.add(UsersResponse.builder()
                    .userId(u.getUserId())
                    .userName(u.getUserName())
                    .build());
        }

        return result;
    }


    public UserResponse detailUser(Long userId) {
        Optional<UserResponse> xx = userRepository.findById(userId);
        if (xx.isPresent()) {
            return xx.get();
        } else {
            // TODO: exception 404
            return null;
        }
    }

    public UserResponse createUser(UserCreateRequest request) {

        String userName = request.getUserName();

        // TODO: validation


        UserResponse result = userRepository.save(UserResponse.builder().userName(request.getUserName()).build());

        // TODO: log

        return result;
    }

}
