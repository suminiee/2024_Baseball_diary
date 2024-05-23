package com.baseball.dto;

import com.baseball.domain.entity.ReviewInfo;
import com.baseball.domain.entity.UserInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewListResponseDto {
    private String title;
    private String updatedAt;
    private String nickname;

    public ReviewListResponseDto(ReviewInfo reviewInfo, UserInfo userInfo) {
        this.title = reviewInfo.getTitle();
        this.updatedAt = reviewInfo.getUpdatedAt();
        this.nickname = userInfo.getNickname();
    }
}
