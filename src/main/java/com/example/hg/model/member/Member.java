package com.example.hg.model.member;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
// @Setter // Entity에서 Setter 는 비권장 (관리포인트 늘어나고 유지보수 어려워짐. 꼭 필요한 경우 메소드 별도 사용 권장)
public class Member {
    @Id
    @GeneratedValue
    private Long id;

    private String username;

    // jpa 내부 proxy 처리 과정에서 entity 객체를 생성하는 과정이 있기 때문에 protected default ctor 필수
    protected Member() {

    }

    public Member(String username) {
        this.username = username;
    }

}
