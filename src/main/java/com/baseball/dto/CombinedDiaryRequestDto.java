package com.baseball.dto;

import lombok.Data;

@Data
public class CombinedDiaryRequestDto {
    private DiarySaveRequestDto diarySaveRequestDto;
    private LineUpNameSaveRequestDto lineUpNameSaveRequestDto;
}
