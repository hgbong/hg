package com.example.hg.model.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UsersResponse {
    private Long userId;

    private String userName;
}
