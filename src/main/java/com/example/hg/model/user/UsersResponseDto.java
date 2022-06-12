package com.example.hg.model.user;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersResponseDto {
    private Long userId;
    private String userName;
    private String userEmail;

    public UsersResponseDto convertUsersResponseDto(User u) {
        UsersResponseDto user = new UsersResponseDto();
        user.setUserId(u.getUserId());
        user.setUserName(u.getUserName());
        return user;
    }
}
