package com.baseball.dto;

import com.baseball.domain.entity.LineUpNameInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LineUpNameResponseDto {
    private String hitter1;
    private String hitter2;
    private String hitter3;
    private String hitter4;
    private String hitter5;
    private String hitter6;
    private String hitter7;
    private String hitter8;
    private String hitter9;
    private String pitcher;

    public LineUpNameResponseDto(LineUpNameInfo lineUpNameInfo) {
        this.hitter1 = lineUpNameInfo.getHitter1();
        this.hitter2 = lineUpNameInfo.getHitter2();
        this.hitter3 = lineUpNameInfo.getHitter3();
        this.hitter4 = lineUpNameInfo.getHitter4();
        this.hitter5 = lineUpNameInfo.getHitter5();
        this.hitter6 = lineUpNameInfo.getHitter6();
        this.hitter7 = lineUpNameInfo.getHitter7();
        this.hitter8 = lineUpNameInfo.getHitter8();
        this.hitter9 = lineUpNameInfo.getHitter9();
        this.pitcher = lineUpNameInfo.getPitcher();
    }
}
