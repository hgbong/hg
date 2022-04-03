package com.example.hg.config;

import com.example.hg.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true) // 컨트롤러에서 @PreAuthorize 어노테이션 사용 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationService authenticationService;

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
        // Spring Security 설정 적용
        http.authorizeRequests()
            .antMatchers("/swagger**").permitAll()
            .anyRequest().authenticated();

        http.formLogin()
            // .loginPage("/auth/login") // loginPage(..) 설정 안할 경우 Spring Security의 기본 로그인 html 표시
            .defaultSuccessUrl("/");

        http.logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout")) // logout 경로
            .logoutSuccessUrl("/login") // logout 성공 시 리다이렉트
            .invalidateHttpSession(true)/*.deleteCookies("JSESSIONID")*/; // logout 성공 시 세션 제거


        // 권한없는 사용자 접근 시 리다이렉트
        // http.exceptionHandling().accessDeniedPage("/auth/denied");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 사용자 인증 검사할 빈 등록
        auth.userDetailsService(authenticationService)
            .passwordEncoder(passwordEncoder());
    }
}
