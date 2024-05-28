package com.baseball.restController;

import com.baseball.dto.ReviewDetailResponseDto;
import com.baseball.dto.ReviewListResponseDto;
import com.baseball.dto.ReviewSaveRequestDto;
import com.baseball.dto.ReviewUpdateRequestDto;
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

    //야구장 리뷰 검색 - 작성자 닉네임
    @GetMapping("/stadiumReview/findReview")
    public ResponseEntity<List<ReviewListResponseDto>> findReviewList(@RequestParam String nickname) {
        List<ReviewListResponseDto> reviewListResponseDtos = reviewService.findReviewByNickname(nickname);
        return ResponseEntity.ok(reviewListResponseDtos);
    }

    //야구장 리뷰 수정
    @PatchMapping("/stadiumReview/detail")
    public ResponseEntity<?> updateReview(@RequestBody ReviewUpdateRequestDto reviewUpdateRequestDto, HttpSession session) {
        try {
            Long userId = (Long) session.getAttribute("userId");
            reviewService.updateReview(reviewUpdateRequestDto, userId);
            return ResponseEntity.ok("리뷰 수정 완료");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("리뷰 정보를 찾을 수 없습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("리뷰 내용 수정 중 오류 발생: " + e.getMessage());
        }
    }

    //야구장 리뷰 삭제
    @DeleteMapping("/stadiumReview/delete")
    public ResponseEntity<?> deleteReview(@RequestParam Long reviewId, HttpSession session) {
        try {
            Long userId = (Long) session.getAttribute("userId");
            reviewService.deleteReview(reviewId, userId);
            return ResponseEntity.ok("리뷰 삭제 완료");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("리뷰를 삭제할 권한이 없습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("리뷰 내용 삭제 중 오류 발생: " + e.getMessage());
        }
    }
}
