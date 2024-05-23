package com.baseball.dto;

import com.baseball.domain.entity.ScoreInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ScoreResponseDto {
    private Long inning1;
    private Long inning2;
    private Long inning3;
    private Long inning4;
    private Long inning5;
    private Long inning6;
    private Long inning7;
    private Long inning8;
    private Long inning9;
    private Long inning10;
    private Long inning11;
    private Long inning12;
    private Long run;
    private Long hit;
    private Long error;
    private Long balls;
    private String type;

    public ScoreResponseDto(ScoreInfo scoreInfo) {
        this.inning1 = scoreInfo.getInning1();
        this.inning2 = scoreInfo.getInning2();
        this.inning3 = scoreInfo.getInning3();
        this.inning4 = scoreInfo.getInning4();
        this.inning5 = scoreInfo.getInning5();
        this.inning6 = scoreInfo.getInning6();
        this.inning7 = scoreInfo.getInning7();
        this.inning8 = scoreInfo.getInning8();
        this.inning9 = scoreInfo.getInning9();
        this.inning10 = scoreInfo.getInning10();
        this.inning11 = scoreInfo.getInning11();
        this.inning12 = scoreInfo.getInning12();
        this.run = scoreInfo.getRun();
        this.hit = scoreInfo.getHit();
        this.error = scoreInfo.getError();
        this.balls = scoreInfo.getBalls();
        this.type = scoreInfo.getType();
    }
}
