package com.soojong.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<Entity클래스,PK타입>을 확장한다(이유 : 기본적인 CRUD 메소드가 자동생성됨!!)
// @Repository 필요없음, Entity클래스와 Entity Repository는 같은 위치에 둘것.
public interface PostsRepository extends JpaRepository<Posts,Long> {


}
