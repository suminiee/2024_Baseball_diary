package com.baseball.controller;

import com.baseball.dto.PasswordUpdateRequestDto;
import com.baseball.dto.UserInfoUpdateRequestDto;
import com.baseball.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //회원정보 변경 - 비밀번호 변경
    @PatchMapping("/changeMyInfo")
    public ResponseEntity<?> updatePassword(@RequestBody PasswordUpdateRequestDto passwordUpdateRequestDto) {
        try {
            userService.updatePassword(passwordUpdateRequestDto);
            return ResponseEntity.status(HttpStatus.OK).body("비밀번호 변경 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("비밀번호 변경 중 오류 발생 " + e.getMessage());
        }
    }

    //회원정보 변경 - 닉네임, 이메일 변경
    @PatchMapping
    public ResponseEntity<?> updateUserInfo(@RequestBody UserInfoUpdateRequestDto userInfoUpdateRequestDto) {
        try {
            userService.updateUserInfo(userInfoUpdateRequestDto);
            return ResponseEntity.status(HttpStatus.OK).body("회원정보 변경 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("회원정보 변경중 오류 발생 " + e.getMessage());
        }
    }

    //회원정보 변경 - 프로필사진 추가/변경

    //응원팀 변경/설정


}
