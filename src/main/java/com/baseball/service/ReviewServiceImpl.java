package com.baseball.service;

import com.baseball.domain.entity.ReviewInfo;
import com.baseball.domain.entity.TeamInfo;
import com.baseball.domain.entity.UserInfo;
import com.baseball.dto.DiaryResponseDto;
import com.baseball.dto.ReviewDetailResponseDto;
import com.baseball.dto.ReviewListResponseDto;
import com.baseball.dto.ReviewSaveRequestDto;
import com.baseball.repository.ReviewRepository;
import com.baseball.repository.TeamRepository;
import com.baseball.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TeamRepository teamRepository;

    //야구장 리뷰 저장
    @Override
    public void saveReview(ReviewSaveRequestDto reviewSaveRequestDto, Long userId) {
        try {
            UserInfo userInfo = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            TeamInfo teamInfo = teamRepository.findById(reviewSaveRequestDto.getTeamId())
                    .orElseThrow(() -> new RuntimeException("Team not found"));

            ReviewInfo reviewInfo = ReviewInfo.builder()
                    .title(reviewSaveRequestDto.getTitle())
                    .content(reviewSaveRequestDto.getContent())
                    .imageAddress(reviewSaveRequestDto.getImageAddress())
                    .visitedAt(reviewSaveRequestDto.getVisitedAt())
                    .userId(userInfo)
                    .teamId(teamInfo)
                    .build();

            reviewRepository.save(reviewInfo);
        } catch (Exception e) {
            log.error("야구장 리뷰 저장 중 오류 발생: {}", e.getMessage());
            throw new RuntimeException("야구장 리뷰 저장 중 오류 발생" + e.getMessage());
        }
    }

    //야구장 리뷰 상세 조회
    @Override
    public ReviewDetailResponseDto getReviewDetail(Long reviewId) {
        Optional<ReviewInfo> reviewInfoOptional = reviewRepository.findById(reviewId);
        return reviewInfoOptional.map(ReviewDetailResponseDto::new).orElse(null);
    }

    //야구장 리뷰 조회
    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<ReviewListResponseDto> findReviewAll() {
        List<ReviewInfo> reviewInfos = reviewRepository.findAll();

        return reviewInfos.stream()
                .map(reviewInfo -> {
                    UserInfo userInfo = userRepository.findById(reviewInfo.getUserId().getUserId())
                            .orElseThrow(() -> new RuntimeException("User not found"));
                    return new ReviewListResponseDto(reviewInfo, userInfo);
                })
                .collect(Collectors.toList());
    }

    //야구장 리뷰 조회 검색 - 닉네임
    @Override
    public List<ReviewListResponseDto> findReviewByNickname(String nickname) {
        List<UserInfo> users = userRepository.findByNicknameContaining(nickname);
        return users.stream()
                .flatMap(user -> reviewRepository.findByUserId(user).stream())
                .map(review -> new ReviewListResponseDto(review, review.getUserId()))
                .collect(Collectors.toList());
    }
}
