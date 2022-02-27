package com.example.hg.service;

import com.example.hg.model.group.Group;
import com.example.hg.model.group.GroupCreateRequestDto;
import com.example.hg.model.group.GroupResponseDto;
import com.example.hg.model.group.GroupsResponseDto;
import com.example.hg.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;


    public List<GroupsResponseDto> listGroups() {
        List<GroupsResponseDto> result = new ArrayList<>();

        Iterable<Group> groups = groupRepository.findAll();
        groups.forEach(g -> {
            result.add(GroupsResponseDto.builder()
                    .groupId(g.getGroupId()).groupName(g.getGroupName()).build());
        });

        return result;
    }


    public GroupResponseDto detailGroup(Long groupId) {
        // TODO 404
        Group g = groupRepository.findById(groupId).orElseThrow(() -> new RuntimeException("group not found"));
        return GroupResponseDto.builder()
                .groupId(g.getGroupId())
                .groupName(g.getGroupName())
                .build();
    }

    public GroupResponseDto createGroup(GroupCreateRequestDto request) {
        // TODO: validation
        String groupName = request.getGroupName();

        // TODO: log
        Group g = groupRepository.save(Group.builder().groupName(request.getGroupName()).build());
        return GroupResponseDto.builder()
                .groupId(g.getGroupId())
                .groupName(g.getGroupName())
                .build();
    }

}
