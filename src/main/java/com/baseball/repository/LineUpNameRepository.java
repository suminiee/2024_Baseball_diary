package com.baseball.repository;

import com.baseball.domain.entity.LineUpNameInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineUpNameRepository extends JpaRepository<LineUpNameInfo, Long> {
}
