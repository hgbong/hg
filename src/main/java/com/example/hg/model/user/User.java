package com.example.hg.model.user;

import com.example.hg.model.group.Group;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@Entity
@Table(name = "iam_user")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "user_name")
    private String userName;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
}
