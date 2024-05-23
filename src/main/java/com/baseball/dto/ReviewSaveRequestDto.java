package com.baseball.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ReviewSaveRequestDto {
    private String title;
    private String content;
    private String imageAddress;
    private String visitedAt;
    private Long teamId;
}
