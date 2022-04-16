package com.example.hg.service;


import com.example.hg.config.OAuthAttributes;
import com.example.hg.model.security.SessionUser;
import com.example.hg.model.user.User;
import com.example.hg.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.Collections;

/**
 * OAuth2 로그인 후처리를 위한 클래스
 * 로그인 이후 가져온 정보(email, picture, name, ..)를 기반으로 가입, 정보 수정, 세션 저장 등을 처리하는 클래스
 */


@Component
@RequiredArgsConstructor
@Transactional
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;

    private final HttpSession httpSession;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // registrationId : 소셜 서비스 구분자 (e.g. "google", "kakao", "naver")
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        // userNameAttributeName : OAuth2 로그인 진행 시 키가 되는 필드값 (구글은 기본적으로 "sub"라는 기본 코드를 지원하지만, 카카오 네이버 등은 지원 안함)
        String userNameAttributeName = userRequest.getClientRegistration()
            .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        // OAuth2Attributes : OAuth2UserService를 통해 가져온 OAuth2User의 속성을 보관하는 클래스
        // oAuth2Uesr.attributes가 Map 타입이므로, 각 필드로 값 변환이 필요
        OAuthAttributes attributes = OAuthAttributes.
            of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        // 소셜 서비스에서 정보 변경 시 client 서비스에서도 동기화
        User user = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionUser(user));

        return new DefaultOAuth2User(
            Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
            attributes.getAttributes(),
            attributes.getNameAttributeKey());
    }

    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByUserEmail(attributes.getEmail())
            .map(entity -> entity.update(attributes.getName(), attributes.getEmail(), attributes.getPicture()))
            .orElse(attributes.toEntity());

        return userRepository.save(user);
    }

}
