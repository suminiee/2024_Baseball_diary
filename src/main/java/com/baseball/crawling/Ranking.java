package com.baseball.crawling;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Ranking {
    private String rank; //순위
    private String team; //팀
    private String gcnts; //경기수
    private String wpnt; //승
    private String lpnt; //패
    private String dpnt; //무
    private String wnrt; //승률
    private String wlpnt; //게임차
    private String streak; //연속
    private String onbase; //출루율
    private String slugging; //장타율
    private String lately; //최근 10경기
}
