package com.baseball.repository;

import com.baseball.domain.entity.DiaryInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<DiaryInfo, Long> {
    boolean existsByGameDate(String gameDate);
}
