package com.example.hg.repository;

import com.example.hg.model.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByUserNameContains(String userName, Pageable pageable);

    List<User> findByUserNameContainsAndUserEmailContains(String userName, String userEmail, Pageable pageable);

}
