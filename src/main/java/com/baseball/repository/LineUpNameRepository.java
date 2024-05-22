package com.baseball.repository;

import com.baseball.domain.entity.DiaryInfo;
import com.baseball.domain.entity.LineUpNameInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LineUpNameRepository extends JpaRepository<LineUpNameInfo, Long> {
    Optional<LineUpNameInfo> findByDiaryId(DiaryInfo diaryId);
}
