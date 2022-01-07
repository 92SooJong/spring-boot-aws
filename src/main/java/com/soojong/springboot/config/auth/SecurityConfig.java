package com.soojong.springboot.config.auth;

import com.soojong.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().headers().frameOptions().disable() //h2-console 화면을 사용하기 위해 해당 옵션들을 disable 처리
            .and()
                .authorizeRequests() // URL별 권한 관리를 설정하는 옵션의 시작점
                // antMatchers 권한 관리 대상을 지정함
                // permitAll()은 어떤 사용자든 접근이 가능하다.
                // "/api/v1/**"은 USER 권한을 가진 사람만 사용이 가능하다
                .antMatchers("/","/css/**","/images/**","/js/**","/h2-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                // 위에 언급된 자원을 제외한 요청(anyRequest())은 권한이 반드시 있어야한다(authenticated())
                .anyRequest().authenticated()
            .and()
                .logout() // 로그아웃과 관련된 설정의 진입점
                    .logoutSuccessUrl("/") // 로그아웃 성공시 "/"로 이동하도록 한다
            .and()
                .oauth2Login() // 로그인과 관련된 설정의 진입점
                    .userInfoEndpoint() // 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당
                    // 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록함
                    // 리소스 서버(소셜 서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시할 수 있음.
                    .userService(customOAuth2UserService);
    }
}
