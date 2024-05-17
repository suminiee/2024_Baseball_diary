package com.baseball.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PasswordUpdateRequestDto {
    private String currentPassword;
    private String newPassword;
}
