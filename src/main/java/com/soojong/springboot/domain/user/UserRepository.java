package com.soojong.springboot.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    // 값이 없음을 명확화게 하기위해 Optional을 사용함.
    Optional<User> findByEmail(String email);

}
