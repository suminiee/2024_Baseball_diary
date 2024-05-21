package com.baseball.service;

import com.baseball.dto.DiarySaveRequestDto;
import com.baseball.dto.LineUpNameSaveRequestDto;

public interface DiaryService {
    Long saveDiaryInfo(DiarySaveRequestDto diarySaveRequestDto, Long userId);

    void saveLineUpNameInfo(LineUpNameSaveRequestDto lineUpNameSaveRequestDto, Long diaryId, Long userId);
}
