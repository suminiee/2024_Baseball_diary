package com.baseball.controller;

import com.baseball.dto.UserRequestDto;
import com.baseball.service.CustomUserDetailService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoginController {
    private final CustomUserDetailService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(HttpServletRequest request, HttpServletResponse response,
                                        @RequestBody UserRequestDto requestDto) {
        try {
            userService.login(request, response, requestDto);
            HttpSession session = request.getSession();
            session.setAttribute("loginId", requestDto.getLoginId()); //로그인 시 session에 loginId 저장(Id는 중복 불가)
            return ResponseEntity.ok("Login successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("로그인 중 오류 발생" + e.getMessage() );
        }

    }
}
