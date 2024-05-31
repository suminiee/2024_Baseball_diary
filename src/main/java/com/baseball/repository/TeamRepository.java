package com.baseball.repository;

import com.baseball.domain.entity.TeamInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<TeamInfo, Long> {
    Optional<TeamInfo> findByTeamName(String teamName);
}
