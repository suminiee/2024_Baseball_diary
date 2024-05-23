package com.baseball.controller;

import com.baseball.domain.entity.ReviewInfo;
import com.baseball.dto.DiaryResponseDto;
import com.baseball.dto.ReviewDetailResponseDto;
import com.baseball.dto.ReviewListResponseDto;
import com.baseball.dto.ReviewSaveRequestDto;
import com.baseball.service.ReviewService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    //야구장 리뷰 등록
    @PostMapping("/stadiumReview/add")
    ResponseEntity<?> addReview(@RequestBody ReviewSaveRequestDto reviewSaveRequestDto, HttpSession session) {
        try {
            Long userId = (Long) session.getAttribute("userId");
            reviewService.saveReview(reviewSaveRequestDto, userId);
            return ResponseEntity.status(HttpStatus.OK).body("야구장 리뷰 저장 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("야구장 리뷰 저장 중 오류 발생 " + e.getMessage());
        }
    }

    //야구장 리뷰 상세 조회
    @GetMapping("/stadiumReview/detail")
    ResponseEntity<ReviewDetailResponseDto> getReviewDetail(@RequestParam Long reviewId, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        ReviewDetailResponseDto reviewDetailResponseDto = reviewService.getReviewDetail(reviewId);

        if (reviewDetailResponseDto != null) {
            return ResponseEntity.ok(reviewDetailResponseDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //야구장 리뷰 리스트로 조회
    @GetMapping("/stadiumReview")
    public ResponseEntity<List<ReviewListResponseDto>> getReviewList() {
        List<ReviewListResponseDto> reviewListResponseDto = reviewService.findReviewAll();
        return ResponseEntity.ok(reviewListResponseDto);
    }

    //야구장 리뷰 검색 - 제목
    @GetMapping("/stadiumReview/findReview")
    public ResponseEntity<List<ReviewListResponseDto>> findReviewList(@RequestParam String nickname) {
        List<ReviewListResponseDto> reviewListResponseDtos = reviewService.findReviewByNickname(nickname);
        return ResponseEntity.ok(reviewListResponseDtos);
    }
}
