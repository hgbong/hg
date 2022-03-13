package com.example.hg.model.usergroup;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserGroupAddRequestDto {

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private Long userId;

    @ApiModelProperty(value = "groupIds")
    private List<Long> groupIds;
}
