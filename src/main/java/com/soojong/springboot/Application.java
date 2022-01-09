package com.soojong.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing // JPA Auditing 사용가능하도록 셋팅. Test에 영향을 줄수 있기때문에 JpaConfig에서 관리하도록 함.
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args); // 내장된 WAS를 실행한다.
    }

}
