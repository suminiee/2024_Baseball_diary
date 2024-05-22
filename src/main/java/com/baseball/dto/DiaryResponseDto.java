package com.baseball.dto;

import com.baseball.domain.entity.DiaryInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DiaryResponseDto {
    private String away;
    private String home;
    private String gameDate;
    private Long awayScore;
    private Long homeScore;
    private String mvp;
    private String stadium;
    private String time;
    private String watch;

    public DiaryResponseDto(DiaryInfo diaryInfo) {
        this.away = diaryInfo.getAway();
        this.home = diaryInfo.getHome();
        this.gameDate = diaryInfo.getGameDate();
        this.awayScore = diaryInfo.getAwayScore();
        this.homeScore = diaryInfo.getHomeScore();
        this.mvp = diaryInfo.getMvp();
        this.stadium = diaryInfo.getStadium();
        this.time = diaryInfo.getTime();
        this.watch = diaryInfo.getWatch();
    }
}
