package com.example.hg.repository;

import com.example.hg.model.group.Group;
import com.example.hg.model.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByUserNameContains(String userName, Pageable pageable);

    List<User> findByUserNameContainsAndUserEmailContains(String userName, String userEmail, Pageable pageable);

    @Query("select g from User u " +
        "join u.userGroups ug " +
        "join ug.group g " +
        "where u.userId = :userId"
    )
    List<Group> findAllGroupsByUserId(Long userId);

//    @Query("select u, ug from User u " +
//        "join fetch u.userGroups ug"
//    )
//    List<User> test(Long userId);
}
