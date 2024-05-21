package com.baseball.service;

import com.baseball.dto.DiarySaveRequestDto;
import com.baseball.dto.LineUpNameSaveRequestDto;
import com.baseball.dto.LineUpPositionSaveRequestDto;
import com.baseball.dto.ScoreSaveRequestDto;

public interface DiaryService {
    Long saveDiaryInfo(DiarySaveRequestDto diarySaveRequestDto, Long userId);

    void saveLineUpNameInfo(LineUpNameSaveRequestDto lineUpNameSaveRequestDto, Long diaryId, Long userId);

    void saveLineUpPositionInfo(LineUpPositionSaveRequestDto lineUpPositionSaveRequestDto, Long diaryId, Long userId);

    void saveScoreInfo(ScoreSaveRequestDto scoreSaveRequestDto, Long diaryId, Long userId);
}
