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
import java.util.Optional;

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
        Optional<Group> group = groupRepository.findById(groupId);
        if (group.isPresent()) {
            Group g = group.get();
            return GroupResponseDto.builder()
                    .groupId(g.getGroupId())
                    .groupName(g.getGroupName())
                    .build();
        } else {
            // TODO: exception 404
            return null;
        }
    }

    public GroupResponseDto createGroup(GroupCreateRequestDto request) {
        String groupName = request.getGroupName();

        // TODO: validation


        Group g = groupRepository.save(Group.builder().groupName(request.getGroupName()).build());

        // TODO: log


        return GroupResponseDto.builder()
                .groupId(g.getGroupId())
                .groupName(g.getGroupName())
                .build();
    }

}
