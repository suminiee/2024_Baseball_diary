package com.baseball.dto;

import com.baseball.domain.entity.DiaryInfo;
import com.baseball.domain.entity.UserInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LineUpNameSaveRequestDto {
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
}
