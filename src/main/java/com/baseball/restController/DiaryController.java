package com.baseball.restController;

import com.baseball.domain.entity.DiaryInfo;
import com.baseball.domain.entity.UserInfo;
import com.baseball.dto.*;
import com.baseball.repository.DiaryRepository;
import com.baseball.repository.UserRepository;
import com.baseball.service.DiaryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    @Autowired
    UserRepository userRepository;
    @Autowired
    DiaryRepository diaryRepository;

    //야구 일기 저장
    @PostMapping("/diary/add")
    public ResponseEntity<?> addDiary(@RequestBody ObjectNode saveObj, HttpSession session) {
        try {
            Long userId = (Long) session.getAttribute("userId");
            ObjectMapper mapper = new ObjectMapper();
            DiarySaveRequestDto diarySaveRequestDto = mapper.treeToValue(saveObj.get("diarySaveRequestDto"), DiarySaveRequestDto.class);
            LineUpNameSaveRequestDto lineUpNameSaveRequestDto = mapper.treeToValue(saveObj.get("lineUpNameSaveRequestDto"), LineUpNameSaveRequestDto.class);
            LineUpPositionSaveRequestDto lineUpPositionSaveRequestDto = mapper.treeToValue(saveObj.get("lineUpPositionSaveRequestDto"), LineUpPositionSaveRequestDto.class);
//            ScoreSaveRequestDto scoreSaveRequestDto = mapper.treeToValue(saveObj.get("scoreSaveRequestDto"), ScoreSaveRequestDto.class);

            Long diaryId = diaryService.saveDiaryInfo(diarySaveRequestDto, userId);
            diaryService.saveLineUpNameInfo(lineUpNameSaveRequestDto, diaryId, userId);
            diaryService.saveLineUpPositionInfo(lineUpPositionSaveRequestDto, diaryId, userId);
//            diaryService.saveScoreInfo(scoreSaveRequestDto, diaryId, userId);

            return ResponseEntity.status(HttpStatus.OK).body("야구 일기 저장 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("야구 일기 저장 중 오류 발생 " + e.getMessage());
        }

    }

    //userId와 날짜(gameDate)를 통해서 diaryId 조회
    @GetMapping("/diary/getId")
    public ResponseEntity<?> getDiaryId(@RequestParam String gameDate, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        UserInfo userInfo = userRepository.findByUserId(userId);

        System.out.println("UserInfo: " + userInfo);
        System.out.println("GameDate: " + gameDate);

        Optional<DiaryInfo> optionalDiaryInfo = diaryRepository.getDiaryInfoByUserIdAndGameDate(userInfo, gameDate);

        if (optionalDiaryInfo.isPresent()) {
            Long diaryId = optionalDiaryInfo.get().getDiaryId();
            return ResponseEntity.ok(diaryId);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Diary not found");
        }

//        Long diaryId = diaryRepository.getDiaryInfoByUserIdAndGameDate(userInfo, gameDate).getDiaryId();
//        return diaryId;
    }


    //야구 일기 상세 조회
    @GetMapping("/diary/info")
    public ResponseEntity<Map<String, Object>> getDiaryDetail(@RequestParam Long diaryId) {
        DiaryResponseDto diaryResponseDto = diaryService.findDiaryByDiaryId(diaryId);
        LineUpNameResponseDto lineUpNameResponseDto = diaryService.findLineUpNameByDiaryId(diaryId);
        LineUpPositionResponseDto lineUpPositionResponseDto = diaryService.findLineUpPositionByDiaryId(diaryId);
//        ScoreResponseDto scoreResponseDto = diaryService.findScoreByDiaryId(diaryId);

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


        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    //야구 일기 상세 수정
    @PatchMapping("/diary/info")
    public ResponseEntity<?> updateDiaryInfo(@RequestBody ObjectNode saveObj, @RequestParam Long diaryId, HttpSession session) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            DiarySaveRequestDto diarySaveRequestDto = mapper.treeToValue(saveObj.get("diarySaveRequestDto"), DiarySaveRequestDto.class);
            LineUpNameSaveRequestDto lineUpNameSaveRequestDto = mapper.treeToValue(saveObj.get("lineUpNameSaveRequestDto"), LineUpNameSaveRequestDto.class);
            LineUpPositionSaveRequestDto lineUpPositionSaveRequestDto = mapper.treeToValue(saveObj.get("lineUpPositionSaveRequestDto"), LineUpPositionSaveRequestDto.class);
//            ScoreSaveRequestDto scoreSaveRequestDto = mapper.treeToValue(saveObj.get("scoreSaveRequestDto"), ScoreSaveRequestDto.class);

            diaryService.updateDiary(diarySaveRequestDto, diaryId);
            diaryService.updateLineUpName(lineUpNameSaveRequestDto, diaryId);
            diaryService.updateLineUpPosition(lineUpPositionSaveRequestDto, diaryId);
//            diaryService.updateScore(scoreSaveRequestDto, diaryId);

            return ResponseEntity.status(HttpStatus.OK).body("야구 일기 업데이트 성공");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("야구 일기 업데이트 중 오류 발생 " + e.getMessage());
        }
    }

    //주어진 날짜에 일기 존재하는지 확인
    @GetMapping("/api/diary/check")
    public ResponseEntity<Map<String, Boolean>> checkDiary(@RequestParam String date) {
        boolean exists = diaryService.existsByDate(date);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return ResponseEntity.ok(response);
    }
    // 일기 쓰기 페이지로 이동



}
