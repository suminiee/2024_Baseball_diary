package com.baseball.service;


import com.baseball.dto.PasswordUpdateRequestDto;
import com.baseball.dto.UserInfoUpdateRequestDto;

public interface UserService {
    void updatePassword(PasswordUpdateRequestDto passwordUpdateRequestDto);

    void updateUserInfo(UserInfoUpdateRequestDto userInfoUpdateRequestDto);

    void updateMyTeam(String myTeam, String loginId);

}
