package com.baseball.controller;

import com.baseball.dto.DiarySaveRequestDto;
import com.baseball.dto.LineUpNameSaveRequestDto;
import com.baseball.dto.LineUpPositionSaveRequestDto;
import com.baseball.dto.ScoreSaveRequestDto;
import com.baseball.service.DiaryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    //야구 일기 저장
    @PostMapping("/diary/add")
    public ResponseEntity<?> addDiary(@RequestBody ObjectNode saveObj, HttpSession session) {
        try {
            Long userId = (Long) session.getAttribute("userId");
            ObjectMapper mapper = new ObjectMapper();
            DiarySaveRequestDto diarySaveRequestDto = mapper.treeToValue(saveObj.get("diarySaveRequestDto"), DiarySaveRequestDto.class);
            LineUpNameSaveRequestDto lineUpNameSaveRequestDto = mapper.treeToValue(saveObj.get("lineUpNameSaveRequestDto"), LineUpNameSaveRequestDto.class);
            LineUpPositionSaveRequestDto lineUpPositionSaveRequestDto = mapper.treeToValue(saveObj.get("lineUpPositionSaveRequestDto"), LineUpPositionSaveRequestDto.class);
            ScoreSaveRequestDto scoreSaveRequestDto = mapper.treeToValue(saveObj.get("scoreSaveRequestDto"), ScoreSaveRequestDto.class);

            Long diaryId = diaryService.saveDiaryInfo(diarySaveRequestDto, userId);
            diaryService.saveLineUpNameInfo(lineUpNameSaveRequestDto, diaryId, userId);
            diaryService.saveLineUpPositionInfo(lineUpPositionSaveRequestDto, diaryId, userId);
            diaryService.saveScoreInfo(scoreSaveRequestDto, diaryId, userId);

            return ResponseEntity.status(HttpStatus.OK).body("야구 일기 저장 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("야구 일기 저장 중 오류 발생 " + e.getMessage());
        }

    }
}
