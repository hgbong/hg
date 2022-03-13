package com.example.hg.model.user;

public class UserResponseDto extends UsersResponseDto {
    public static UserResponseDto convertUserResponseDto(User u) {
        UserResponseDto user = new UserResponseDto();
        user.setUserId(u.getUserId());
        user.setUserName(u.getUserName());
        return user;
    }
}
