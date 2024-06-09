package com.baseball.service;


import com.baseball.domain.entity.UserInfo;
import com.baseball.dto.MyTeamResponseDto;
import com.baseball.dto.PasswordUpdateRequestDto;
import com.baseball.dto.UserInfoResponseDto;
import com.baseball.dto.UserInfoUpdateRequestDto;

public interface UserService {
    void updatePassword(PasswordUpdateRequestDto passwordUpdateRequestDto);

    void updateUserInfo(UserInfoUpdateRequestDto userInfoUpdateRequestDto);

    void updateMyTeam(String myTeam, String loginId);

    Long findUserId(String loginId);

    MyTeamResponseDto getMyTeam(Long userId);

    UserInfoResponseDto getUserInfo(Long userId);

}
