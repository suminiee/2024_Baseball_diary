package com.baseball.restController;

import com.baseball.dto.IsDuplicatedIdRequestDto;
import com.baseball.dto.IsDuplicatedNicknameRequestDto;
import com.baseball.dto.SignUpRequestDto;
import com.baseball.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class SignUpController {

    private final SignUpService signUpService;

    //회원가입
    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        try {
            signUpService.save(signUpRequestDto);
            return ResponseEntity.status(HttpStatus.OK).body("회원가입 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("회원가입중 오류 발생 " + e.getMessage());
        }
    }

    //아이디 중복확인
    @PostMapping("/signUp/isDuplicatedId")
    public ResponseEntity<Boolean> isDuplicatedId(@RequestBody IsDuplicatedIdRequestDto isDuplicatedIdRequestDto) {
        try{
            boolean isDuplicated = signUpService.isDuplicatedId(isDuplicatedIdRequestDto);
            return ResponseEntity.ok(isDuplicated);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(false); //실패하면 false 반환
        }
    }

    //닉네임 중복확인
    @PostMapping("/signUp/isDuplicatedNickname")
    public ResponseEntity<Boolean> isDuplicatedNickname(@RequestBody IsDuplicatedNicknameRequestDto isDuplicatedNicknameRequestDto) {
        try{
            boolean isDuplicated = signUpService.isDuplicatedNickname(isDuplicatedNicknameRequestDto);
            return ResponseEntity.ok(isDuplicated);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(false); //실패하면 false 반환
        }
    }

}