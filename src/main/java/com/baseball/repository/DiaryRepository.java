package com.baseball.repository;

import com.baseball.domain.entity.DiaryInfo;
import com.baseball.domain.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DiaryRepository extends JpaRepository<DiaryInfo, Long> {
    boolean existsByGameDate(String gameDate);
    Optional<DiaryInfo> getDiaryInfoByUserIdAndGameDate(UserInfo userId, String gameDate);
}
