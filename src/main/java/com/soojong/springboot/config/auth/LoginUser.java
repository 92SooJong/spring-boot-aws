package com.soojong.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) // 어노테이션이 사용될수 있는 위치는 파라미터이다.
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
}
