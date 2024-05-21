package com.baseball.controller;

import com.baseball.domain.entity.UserInfo;
import com.baseball.dto.CombinedDiaryRequestDto;
import com.baseball.dto.DiarySaveRequestDto;
import com.baseball.dto.LineUpNameSaveRequestDto;
import com.baseball.dto.LineUpPositionSaveRequestDto;
import com.baseball.service.DiaryService;
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
    public ResponseEntity<?> addDiary(@RequestBody CombinedDiaryRequestDto combinedDiaryRequestDto, HttpSession session) {
        try {
            Long userId = (Long) session.getAttribute("userId");
            Long diaryId = diaryService.saveDiaryInfo(combinedDiaryRequestDto.getDiarySaveRequestDto(), userId);
//            System.out.println("**********************" + diaryId); 제대로 반환되는거 확인!
            diaryService.saveLineUpNameInfo(combinedDiaryRequestDto.getLineUpNameSaveRequestDto(), diaryId, userId);

            return ResponseEntity.status(HttpStatus.OK).body("야구 일기 저장 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("야구 일기 저장 중 오류 발생 " + e.getMessage());
        }

    }
}
