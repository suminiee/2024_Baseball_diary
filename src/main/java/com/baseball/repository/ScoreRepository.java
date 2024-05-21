package com.baseball.repository;

import com.baseball.domain.entity.ScoreInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<ScoreInfo, Long> {
}
