package com.example.hg.model.user;

import com.example.hg.config.SpringContext;
import com.example.hg.model.group.Group;
import com.example.hg.model.group.GroupsResponseDto;
import com.example.hg.repository.GroupRepository;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class UserResponseDto extends UsersResponseDto {

    private GroupRepository groupRepository;

    @Getter
    @Setter
    private List<GroupsResponseDto> groups;

    public UserResponseDto convertUserResponseDto(User u) {
        // TODO 개선점 고려 (repository를 여기서 아래와같이 가져와 사용하지 않고, 서비스 단에서 처리 고려)
        if (groupRepository == null) {
            groupRepository = SpringContext.getBean(GroupRepository.class);
        }

        UserResponseDto user = new UserResponseDto();
        user.setUserId(u.getUserId());
        user.setUserName(u.getUserName());

        List<GroupsResponseDto> groups = new ArrayList<>();
        u.getUserGroups().forEach(ug -> {
            Group g = groupRepository.findById(ug.getGroup().getGroupId()).orElseThrow(() -> new RuntimeException("group not found"));
            groups.add(GroupsResponseDto.builder()
                    .groupId(g.getGroupId())
                    .groupName(g.getGroupName())
                    .build());
        });

        user.setGroups(groups);
        return user;
    }
}
