package com.example.hg.service;

import com.example.hg.model.group.Group;
import com.example.hg.model.group.GroupCreateRequestDto;
import com.example.hg.model.group.GroupResponseDto;
import com.example.hg.model.group.GroupsResponseDto;
import com.example.hg.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class GroupService {

    private final GroupRepository groupRepository;

    @PostConstruct
    public void init() {
        groupRepository.save(Group.builder().groupName("group1").build());
        groupRepository.save(Group.builder().groupName("group2").build());
        groupRepository.save(Group.builder().groupName("group3").build());
    }

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
        // TODO exception 생성 및 global exception handler 처리
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
