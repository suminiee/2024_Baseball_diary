package com.baseball.repository;

import com.baseball.domain.entity.DiaryInfo;
import com.baseball.domain.entity.LineUpPositionInfo;
import com.baseball.domain.entity.ScoreInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScoreRepository extends JpaRepository<ScoreInfo, Long> {
    Optional<ScoreInfo> findByDiaryId(DiaryInfo diaryId);
}
