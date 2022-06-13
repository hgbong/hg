package com.example.hg.config;

import com.example.hg.model.security.Role;
import com.example.hg.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true) // 컨트롤러에서 @PreAuthorize 어노테이션 사용 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // Spring Security 선적용 (아래 configuire(HttpSecurity http) 보다 우선적으로 적용됨)
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "/swagger-ui.html");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // h2-console 화면 사용을 위해 disable 설정
        http.csrf().disable()
            .headers().frameOptions().disable()
            .and()
            .authorizeRequests() // URL 권한 관리 설정 옵션의 시작점 (antMatchers를 사용하기 위한)
            .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/auth/join").permitAll()
            .antMatchers("/api/v1/**").hasRole(Role.USER.name())
            .anyRequest().authenticated() // authenticated: 인증된 사용자
            .and()
            .logout()
            .logoutSuccessUrl("/")
            .and()
            .oauth2Login() // oauth2 로그인 설정 관련 진입점
            .userInfoEndpoint() // oauth2 로그인 성공 이후 사용자 정보를 가져오기 위한 설정
            .userService(customOAuth2UserService); // 소셜 로그인 성공 이후 후속 처리를 위한 객체를 지정. UserService

//        http.formLogin()
//            // .loginPage("/auth/login") // loginPage(..) 설정 안할 경우 Spring Security의 기본 로그인 html 표시
//            .defaultSuccessUrl("/");


        // 권한없는 사용자 접근 시 리다이렉트
        // http.exceptionHandling().accessDeniedPage("/auth/denied");
    }

//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        // 사용자 인증 검사할 빈 등록
//        auth.userDetailsService(authenticationService)
//            .passwordEncoder(passwordEncoder());
//    }
}
