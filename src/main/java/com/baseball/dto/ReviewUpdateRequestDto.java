package com.baseball.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewUpdateRequestDto {
    private String title;
    private String content;
    private String imageAddress;
    private String visitedAt;
    private Long teamId;
    private Long reviewId;
}
