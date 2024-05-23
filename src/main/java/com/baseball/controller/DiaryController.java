package com.baseball.controller;

import com.baseball.dto.*;
import com.baseball.service.DiaryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    //야구 일기 상세 조회
    @GetMapping("/diary/info")
    public ResponseEntity<Map<String, Object>> getDiaryDetail(@RequestParam Long diaryId) {
        DiaryResponseDto diaryResponseDto = diaryService.findDiaryByDiaryId(diaryId);
        LineUpNameResponseDto lineUpNameResponseDto = diaryService.findLineUpNameByDiaryId(diaryId);
        LineUpPositionResponseDto lineUpPositionResponseDto = diaryService.findLineUpPositionByDiaryId(diaryId);
        ScoreResponseDto scoreResponseDto = diaryService.findScoreByDiaryId(diaryId);

        Map<String, Object> responseData = new HashMap<>();

        if (diaryResponseDto != null) {
            responseData.put("diary", diaryResponseDto);
        } else {
            return ResponseEntity.notFound().build();
        }
        if (lineUpNameResponseDto != null) {
            responseData.put("lineUpName", lineUpNameResponseDto);
        } else {
            return ResponseEntity.notFound().build();
        }
        if (lineUpPositionResponseDto != null) {
            responseData.put("lineUpPosition", lineUpPositionResponseDto);
        } else {
            return ResponseEntity.notFound().build();
        }
        if (scoreResponseDto != null) {
            responseData.put("score", scoreResponseDto);
        } else {
            return ResponseEntity.notFound().build();
        }


        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    //야구 일기 상세 수정
    @PatchMapping("/diary/info")
    public ResponseEntity<?> updateDiaryInfo(@RequestBody ObjectNode saveObj, @RequestParam Long diaryId) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            DiarySaveRequestDto diarySaveRequestDto = mapper.treeToValue(saveObj.get("diarySaveRequestDto"), DiarySaveRequestDto.class);
            LineUpNameSaveRequestDto lineUpNameSaveRequestDto = mapper.treeToValue(saveObj.get("lineUpNameSaveRequestDto"), LineUpNameSaveRequestDto.class);
            LineUpPositionSaveRequestDto lineUpPositionSaveRequestDto = mapper.treeToValue(saveObj.get("lineUpPositionSaveRequestDto"), LineUpPositionSaveRequestDto.class);
            ScoreSaveRequestDto scoreSaveRequestDto = mapper.treeToValue(saveObj.get("scoreSaveRequestDto"), ScoreSaveRequestDto.class);

            diaryService.updateDiary(diarySaveRequestDto, diaryId);
            diaryService.updateLineUpName(lineUpNameSaveRequestDto, diaryId);
            diaryService.updateLineUpPosition(lineUpPositionSaveRequestDto, diaryId);
            diaryService.updateScore(scoreSaveRequestDto, diaryId);


            return ResponseEntity.status(HttpStatus.OK).body("야구 일기 업데이트 성공");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("야구 일기 업데이트 중 오류 발생 " + e.getMessage());
        }
    }
}
