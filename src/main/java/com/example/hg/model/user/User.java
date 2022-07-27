package com.example.hg.model.user;

import com.example.hg.model.security.Role;
import com.example.hg.model.usergroup.UserGroup;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@Table(name = "iam_user")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    // TODO 컬럼 순서 지정 (hibernate는 default로 알파벳 순서로 정렬하여 컬럼을 생성함. (vm에 따라 컬럼 순서가 바뀔 수 있기 때문이라고 함)


    @Id
    @Column(name = "user_id")
    @GeneratedValue
    private Long userId;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "user_email", nullable = false)
    private String userEmail;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserGroup> userGroups;

    // @Enumerated : JPA에서 enum 타입을 어떻게 저장할지 지정. default = EnumType.ORDINAL
    // 그러나 default 값은 절대 사용하지 말아야 하는데, 그 이유는 default를 사용할 경우 DB에 int 로 값이 저장되기 때문임
    // 만약 enum 목록이 변경되어 각 enum들의 int 값이 기존과 달라지면, 모든 DB 데이터가 기존 권한과 정합성이 틀려지는 심각한 문제 발생
    // 따라서 EnumType.STRING 으로 지정하여, 명시적으로 값을 지정하여 입력
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column
    private String picture;

    public String getRoleKey() {
        return this.role.getKey();
    }

    public User update(String name, String email, String picture) {
        this.userName = name;
        this.userEmail = email;
        this.picture = picture;

        return this;
    }
}
