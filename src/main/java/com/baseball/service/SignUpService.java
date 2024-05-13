package com.baseball.service;

import com.baseball.dto.IsDuplicatedIdRequestDto;
import com.baseball.dto.IsDuplicatedNicknameRequestDto;
import com.baseball.dto.SignUpRequestDto;

public interface SignUpService {
    void save(SignUpRequestDto signUpRequestDto);

    boolean isDuplicatedId(IsDuplicatedIdRequestDto isDuplicatedIdRequestDto);

    boolean isDuplicatedNickname(IsDuplicatedNicknameRequestDto isDuplicatedNicknameRequestDto);
}
