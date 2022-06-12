package com.example.hg.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserJoinRequestDto {

    @JsonIgnore
    @ApiModelProperty(value = "사용자ID", example = "0")
    private String userId;

    @NotNull
    @ApiModelProperty(value = "사용자이름", example = "username")
    private String userName;

    @NotNull
    @Email
    @ApiModelProperty(value = "사용자이메일", example = "test@gmail.com")
    private String userEmail;

    @NotNull
    @Size(min = 8)
    @ApiModelProperty(value = "비밀번호", example = "password")
    private String password; // TODO 비밀번호 암호화 / regEx 검사

}
