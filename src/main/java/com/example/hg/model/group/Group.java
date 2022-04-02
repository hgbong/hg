package com.example.hg.model.group;

import com.example.hg.model.usergroup.UserGroup;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@Table(name = "iam_group")
@NoArgsConstructor
@AllArgsConstructor
public class Group {

    @Id
    @Column(name = "group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupId;

    @Column(name = "group_name")
    private String groupName;

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    private List<UserGroup> userGroups;

}
