package com.soojong.springboot.config.auth;

import com.soojong.springboot.config.auth.dto.OAuthAttributes;
import com.soojong.springboot.config.auth.dto.SessionUser;
import com.soojong.springboot.domain.user.User;
import com.soojong.springboot.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final HttpSession httpSession;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // 로그인을 진행중인 서비스를 구분 ex) Google , Kakao
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        // OAuth2 로그인 진행 시 키가되는 필드값. Primary Key느낌
        // 구글의 경우 기본적으로 코드(구글의 기본 코드 : sub)를 지원하지만 네이버,카카오는 지원하지 않음
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().
                getUserInfoEndpoint().getUserNameAttributeName();
        // OAuth2UserService를 통해 가져온 OAuth2User의 attribute를 가지고 있다.
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        User user = saveOrUpdate(attributes);
        // 세션에 사용자 정보를 저장할때 SeesionUser를 사용하도록 한다
        // Q. User Entity와 필드가 상당히 비슷한데 왜 그대로 사용하지 않고 SessionUser를 별도로 만들어서 사용하는가??
        // A. 세션을 등록하기 위해선 직렬화가 필요한데 User Entity를 직렬화 해버리면 자식이나 관계를 맺고있는 모든 클래스가
        // 직렬화를 수행해버린다. 성능이슈나 부수효과가 발생할 수 있음.
        httpSession.setAttribute("user",new SessionUser(user));

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                                     attributes.getAttributes(), attributes.getNameAttributeKey());

    }

    private User saveOrUpdate(OAuthAttributes attributes){
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());

        return userRepository.save(user);
    }

}
