package com.baseball.repository;

import com.baseball.domain.entity.DiaryInfo;
import com.baseball.domain.entity.LineUpNameInfo;
import com.baseball.domain.entity.LineUpPositionInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LineUpPositionRepository extends JpaRepository<LineUpPositionInfo, Long> {
    Optional<LineUpPositionInfo> findByDiaryId(DiaryInfo diaryId);
}
