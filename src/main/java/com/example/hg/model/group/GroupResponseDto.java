package com.example.hg.model.group;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupResponseDto {

    private Long groupId;

    private String groupName;
}
