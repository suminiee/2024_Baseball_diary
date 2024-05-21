package com.baseball.repository;

import com.baseball.domain.entity.LineUpPositionInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineUpPositionRepository extends JpaRepository<LineUpPositionInfo, Long> {
}
