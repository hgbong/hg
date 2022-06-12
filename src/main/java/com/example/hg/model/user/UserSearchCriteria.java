package com.example.hg.model.user;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

/**
 * 사용자 검색 조건
 */
@Getter
@Setter
public class UserSearchCriteria {

    @ApiParam(value = "사용자이름", example = "username")
    private String userName;

    @ApiParam(value = "사용자이메일", example = "test@gmail.com")
    private String userEmail;
}
