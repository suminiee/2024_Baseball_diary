package com.baseball.repository;

import com.baseball.domain.entity.ReviewInfo;
import com.baseball.domain.entity.UserInfo;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewInfo, Long> {
    List<ReviewInfo> findByUserId(UserInfo userId);
}
