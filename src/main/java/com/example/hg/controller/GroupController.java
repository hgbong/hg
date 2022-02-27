package com.example.hg.controller;

import com.example.hg.model.group.GroupCreateRequestDto;
import com.example.hg.model.group.GroupResponseDto;
import com.example.hg.model.group.GroupsResponseDto;
import com.example.hg.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping
    public List<GroupsResponseDto> listGroups() {
        return groupService.listGroups();
    }

    @GetMapping("/{groupId}")
    public GroupResponseDto detailGroup(@PathVariable Long groupId) {
        return groupService.detailGroup(groupId);
    }

    @PostMapping
    public GroupResponseDto createGroup(@RequestBody GroupCreateRequestDto request) {
        return groupService.createGroup(request);
    }
}