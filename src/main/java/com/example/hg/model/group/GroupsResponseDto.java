package com.example.hg.model.group;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GroupsResponseDto {
    private Long groupId;

    private String groupName;
}
