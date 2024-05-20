package com.baseball.service;

import com.baseball.dto.DiarySaveRequestDto;

public interface DiaryService {
    Long saveDiaryInfo(DiarySaveRequestDto diarySaveRequestDto, Long userId);
}
