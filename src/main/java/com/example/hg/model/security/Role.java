package com.example.hg.model.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    // spring security에서 관리하는 역할은 반드시 ROLE_ prefix 설정 필요
    USER("ROLE_USER", "사용자"),
    ADMIN("ROLE_ADMIN", "관리자"),
    GUEST("ROLE_GUEST", "게스트");

    private final String key;
    private final String title;


}
