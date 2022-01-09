package com.soojong.springboot.config.auth;

import com.soojong.springboot.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final HttpSession httpSession;


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 파라미터에 붙어있는 어노테이션이 LoginUser 클래스인지 확인한다.
        boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;
        // 파라미터의 타입이 SessionUser 인지 확인한다.
        boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());
        return isLoginUserAnnotation && isUserClass;
    }

    @Override // 파라미터에 전달할 객체를 생성한다.
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // 파라미터에 httpSession의 속성중 하나인 user 값을 넣어준다
        return httpSession.getAttribute("user");
    }
}
