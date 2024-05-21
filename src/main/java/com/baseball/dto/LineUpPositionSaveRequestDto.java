package com.baseball.dto;

import com.baseball.domain.entity.DiaryInfo;
import com.baseball.domain.entity.UserInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LineUpPositionSaveRequestDto {
    private String position1;
    private String position2;
    private String position3;
    private String position4;
    private String position5;
    private String position6;
    private String position7;
    private String position8;
    private String position9;
}
