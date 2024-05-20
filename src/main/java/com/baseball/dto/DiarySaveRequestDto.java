package com.baseball.dto;

import com.baseball.domain.entity.UserInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class DiarySaveRequestDto {
    private String away;
    private String home;
    private String gameDate;
    private Long awayScore;
    private Long homeScore;
    private String mvp;
    private String stadium;
    private String time;
    private String watch;
}
