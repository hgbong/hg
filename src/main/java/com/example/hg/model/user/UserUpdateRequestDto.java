package com.example.hg.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequestDto {

    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private Long userId;


    // TODO validation. groupId 필수로 받을지 아닐지 여부
    @ApiModelProperty(value = "groupId", example = "1234")
    private Long groupId;
}
