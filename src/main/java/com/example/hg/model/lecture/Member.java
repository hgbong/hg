package com.example.hg.model.lecture;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter // Entity에서 Setter는 비권장 (관리포인트 늘어나고 유지보수 어려워짐. 꼭 필요한 경우 메소드 별도 사용 권장)
@NoArgsConstructor(access = AccessLevel.PROTECTED) // jpa 내부 proxy 처리 과정에서 entity 객체를 생성하는 과정이 있기 때문에 protected default ctor 필수
@ToString(of = {"id", "username", "age"}) // ToString에 연관관계를 갖는 데이터 (team) 은 찍지 않아야 한다. (서로간에 무한루프 돌 수 있음)
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String username;

    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String username) {
        this.username = username;
    }

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        if (team != null) {
            changeTeam(team);
        }
    }

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
