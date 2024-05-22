package com.baseball.dto;

import com.baseball.domain.entity.LineUpNameInfo;
import com.baseball.domain.entity.LineUpPositionInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LineUpPositionResponseDto {
    private String position1;
    private String position2;
    private String position3;
    private String position4;
    private String position5;
    private String position6;
    private String position7;
    private String position8;
    private String position9;

    public LineUpPositionResponseDto(LineUpPositionInfo lineUpPositionInfo) {
        this.position1 = lineUpPositionInfo.getPosition1();
        this.position2 = lineUpPositionInfo.getPosition2();
        this.position3 = lineUpPositionInfo.getPosition3();
        this.position4 = lineUpPositionInfo.getPosition4();
        this.position5 = lineUpPositionInfo.getPosition5();
        this.position6 = lineUpPositionInfo.getPosition6();
        this.position7 = lineUpPositionInfo.getPosition7();
        this.position8 = lineUpPositionInfo.getPosition8();
        this.position9 = lineUpPositionInfo.getPosition9();
    }
}
