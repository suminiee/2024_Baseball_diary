package com.baseball.dto;

import com.baseball.domain.entity.ReviewInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewDetailResponseDto {
    private String title;
    private String content;
    private String imageAddress;
    private String visitedAt;

    public ReviewDetailResponseDto(ReviewInfo reviewInfo) {
        this.title = reviewInfo.getTitle();
        this.content = reviewInfo.getContent();
        this.imageAddress = reviewInfo.getImageAddress();
        this.visitedAt = reviewInfo.getVisitedAt();
    }
}

