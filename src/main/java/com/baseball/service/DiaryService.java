package com.baseball.service;

import com.baseball.domain.entity.DiaryInfo;
import com.baseball.dto.*;

import java.util.List;

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
    void updateLineUpName(LineUpNameSaveRequestDto lineUpNameSaveRequestDto, Long diaryId);
    void updateLineUpPosition(LineUpPositionSaveRequestDto lineUpPositionSaveRequestDto, Long diaryId);
    void updateScore(ScoreSaveRequestDto scoreSaveRequestDto, Long diaryId);
    boolean existsByDate(String date);
}
