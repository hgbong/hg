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

    @ApiParam(value = "userName", example = "영수")
    private String userName;

    @ApiParam(value = "userEmail", example = "test@gmail.com")
    private String userEmail;
}
