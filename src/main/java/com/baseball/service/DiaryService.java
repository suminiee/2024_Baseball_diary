package com.baseball.service;

import com.baseball.dto.*;

public interface DiaryService {
    Long saveDiaryInfo(DiarySaveRequestDto diarySaveRequestDto, Long userId);
    void saveLineUpNameInfo(LineUpNameSaveRequestDto lineUpNameSaveRequestDto, Long diaryId, Long userId);
    void saveLineUpPositionInfo(LineUpPositionSaveRequestDto lineUpPositionSaveRequestDto, Long diaryId, Long userId);
    void saveScoreInfo(ScoreSaveRequestDto scoreSaveRequestDto, Long diaryId, Long userId);
    DiaryResponseDto findDiaryByDiaryId(Long diaryId);
    LineUpNameResponseDto findLineUpNameByDiaryId(Long diaryId);
    LineUpPositionResponseDto findLineUpPositionByDiaryId(Long diaryId);
    ScoreResponseDto findScoreByDiaryId(Long diaryId);
    void updateDiary(DiarySaveRequestDto diarySaveRequestDto, Long diaryId);
    void updateLineUpName(LineUpNameResponseDto lineUpNameResponseDto, Long diaryId);
}
