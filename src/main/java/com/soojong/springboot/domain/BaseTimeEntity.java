package com.soojong.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // BaseTimeEntity를 확장한 Entity들이 createDate와 modifiedDate를 컬럼으로 인식하도록 하기위함.
@EntityListeners(AuditingEntityListener.class) //Auditing 기능 부여
public abstract class BaseTimeEntity {

    @CreatedDate // 저장될때 자동으로 시간이 부여되도록 함.
    private LocalDateTime createdDate;

    @LastModifiedDate // 수정될때 자동으로 시간이 부여되도록 함.
    private LocalDateTime modifiedDate;

}
