package com.baseball.repository;

import com.baseball.domain.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByLoginId (String loginId);
    List<UserInfo> findByNicknameContaining(String nickname);

    UserInfo findByUserId(Long userId);
}
