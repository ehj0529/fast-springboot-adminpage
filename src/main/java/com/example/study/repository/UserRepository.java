package com.example.study.repository;

import com.example.study.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    // select * from user where account = ?; << test03, test01 등. 설정하는 방법.
    Optional<User> findByAccount(String account);

    //where 절에 이메일 조회 생성.
    Optional<User> findByEmail(String email);

    //where 절에 계정과 이메일을 조건으로 생성. "쿼리 메소드" 라고 호칭함.
    Optional<User> findByAccountAndEmail(String account, String email);

}
