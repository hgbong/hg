package com.example.hg.repository;

import com.example.hg.model.user.UserResponse;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserResponse, Long> {

}
