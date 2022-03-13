package com.example.hg.model.usergroup;

import com.example.hg.model.group.Group;
import com.example.hg.model.user.User;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@Entity
@Table(name = "iam_user_group")
@NoArgsConstructor
@AllArgsConstructor
public class UserGroup {

    @Id
    @Column(name = "user_group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userGroupId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;


    // TODO: relation 테이블에서 관리되어야 할 정보들 e.g.생성일, 수정일 등
    //private LocalDateTime createdDate;
}
