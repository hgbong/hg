package com.example.hg.model.user;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto extends UsersResponseDto {
    private String iodid;

    public static UserResponseDto convertUserResponseDto(User u) {
        UserResponseDto user = new UserResponseDto();
        user.setUserId(u.getUserId());
        user.setUserName(u.getUserName());
        return user;
    }
}
