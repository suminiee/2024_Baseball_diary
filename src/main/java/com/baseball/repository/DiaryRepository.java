package com.baseball.repository;

import com.baseball.domain.entity.DiaryInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiaryRepository extends JpaRepository<DiaryInfo, Long> {
    boolean existsByGameDate(String gameDate);
    List<DiaryInfo> findAll();
}
