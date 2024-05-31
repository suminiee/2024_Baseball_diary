package com.baseball.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequestDto {
    private String name;
    private String email;
    private String loginId;
    private String password;
    private String nickname;
    private String myTeam;
}
