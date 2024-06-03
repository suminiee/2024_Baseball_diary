package com.baseball.dto;

import com.baseball.domain.entity.UserInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@NoArgsConstructor
public class UserInfoResponseDto {
    private String loginId;
    private String nickname;
    private String email;

    public UserInfoResponseDto(UserInfo userInfo) {
        this.loginId = userInfo.getLoginId();
        this.nickname = userInfo.getNickname();
        this.email = userInfo.getEmail();
    }

}
