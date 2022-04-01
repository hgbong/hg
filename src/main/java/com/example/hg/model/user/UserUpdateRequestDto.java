package com.example.hg.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserUpdateRequestDto {

    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private Long userId;

    @ApiModelProperty(value = "userName", example = "userName")
    private String userName;

    @ApiModelProperty(value = "userEmail", example = "_test@gmail.com")
    private String userEmail;


    // TODO validation. groupId 필수로 받을지 아닐지 여부
    @ApiModelProperty(value = "groupIds", example = "[1234, 1235]")
    private List<Long> groupIds;
}
