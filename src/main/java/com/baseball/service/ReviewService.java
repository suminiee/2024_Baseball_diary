package com.baseball.service;

import com.baseball.dto.ReviewDetailResponseDto;
import com.baseball.dto.ReviewListResponseDto;
import com.baseball.dto.ReviewSaveRequestDto;

import java.util.List;

public interface ReviewService {
    void saveReview(ReviewSaveRequestDto reviewSaveRequestDto, Long userId);
    ReviewDetailResponseDto getReviewDetail(Long reviewId);
    List<ReviewListResponseDto> findReviewAll();
    List<ReviewListResponseDto> findReviewByNickname(String nickname);
}
