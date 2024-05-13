package com.baseball.repository;

import com.baseball.domain.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SignUpRepository extends JpaRepository<UserInfo, Long> {
    UserInfo findByLoginId(String loginId);
    UserInfo findByNickname(String nickname);
}
