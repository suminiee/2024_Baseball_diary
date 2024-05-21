package com.baseball.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ScoreSaveRequestDto {
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
}
