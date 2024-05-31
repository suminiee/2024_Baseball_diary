package com.baseball.restController;

import com.baseball.dto.MyTeamResponseDto;
import com.baseball.dto.PasswordUpdateRequestDto;
import com.baseball.dto.ReviewDetailResponseDto;
import com.baseball.dto.UserInfoUpdateRequestDto;
import com.baseball.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    //회원정보 변경 - 비밀번호 변경
    @PatchMapping("/changeMyInfo/password")
    public ResponseEntity<?> updatePassword(HttpSession session ,@RequestBody PasswordUpdateRequestDto passwordUpdateRequestDto) {
        try {
            String loginId = (String)session.getAttribute("loginId");
            passwordUpdateRequestDto.setLoginId(loginId);
            userService.updatePassword(passwordUpdateRequestDto);
            return ResponseEntity.status(HttpStatus.OK).body("비밀번호 변경 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("비밀번호 변경 중 오류 발생 " + e.getMessage());
        }
    }

    //회원정보 변경 - 닉네임, 이메일 변경
    @PatchMapping("/changeMyInfo")
    public ResponseEntity<?> updateUserInfo(HttpSession session ,@RequestBody UserInfoUpdateRequestDto userInfoUpdateRequestDto) {
        try {
            String loginId = (String)session.getAttribute("loginId");
            userInfoUpdateRequestDto.setLoginId(loginId);
            userService.updateUserInfo(userInfoUpdateRequestDto);
            return ResponseEntity.status(HttpStatus.OK).body("회원정보 변경 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("회원정보 변경중 오류 발생 " + e.getMessage());
        }
    }

    //회원정보 변경 - 프로필사진 추가/변경

    //내가 응원하는 팀이름 + 팀 로고 이미지주소 조회
    @GetMapping("/getMyTeam")
    public ResponseEntity<MyTeamResponseDto> getMyTeam(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");

        MyTeamResponseDto myTeamResponseDto = userService.getMyTeam(userId);
        return ResponseEntity.ok(myTeamResponseDto);
    }

    //응원팀 변경/설정
    @PutMapping("/changeMyTeam")
    public ResponseEntity<?> changeMyTeam(HttpSession session, @RequestParam String myTeam) {
        try {
            String loginId = (String)session.getAttribute("loginId");
            System.out.println(myTeam);
            userService.updateMyTeam(loginId, myTeam);
            return ResponseEntity.status(HttpStatus.OK).body("나의 응원팀 변경 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("나의 응원팀 변경중 오류 발생 " + e.getMessage());
        }
    }


}
