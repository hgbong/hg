package com.example.hg.model.security;

import com.example.hg.model.user.User;
import lombok.Getter;

import java.io.Serializable;

/**
 * HTTP 세션에 저장할 사용자 클래스
 * 기존의 User 클래스를 사용하지 않는 이유는, 세션에 저장하려는 DTO 클래스는 Serializable을 구현해야 함
 * 그러나 User 엔티티에서 직렬화를 구현할 경우 엔티티가 POJO가 아니게 되고, 연관관계에 있는 엔티티까지 직렬화 대상에 포함되게 되어,
 * 성능 이슈 등의 사이드 이펙이 발생할 수 있음
 */
@Getter
public class SessionUser implements Serializable {

    private String userName;
    private String userEmail;
    private String picture;

    public SessionUser(User user) {
        this.userName = user.getUserName();
        this.userEmail = user.getUserEmail();
        this.picture = user.getPicture();
    }
}
