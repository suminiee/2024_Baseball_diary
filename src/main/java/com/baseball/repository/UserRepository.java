package com.baseball.repository;

import com.baseball.domain.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByPassword(String currentPassword);

    Optional<UserInfo> findByLoginId (String loginId);
}
