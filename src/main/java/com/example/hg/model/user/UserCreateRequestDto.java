package com.example.hg.model.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequestDto {

    @ApiModelProperty(value = "사용자이름", example = "username")
    private String userName;

    @ApiModelProperty(value = "사용자이메일", example = "test@gmail.com")
    private String userEmail;
}
