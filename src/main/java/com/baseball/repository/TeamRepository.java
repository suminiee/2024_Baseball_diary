package com.baseball.repository;

import com.baseball.domain.entity.TeamInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<TeamInfo, Long> {
}
