package com.baseball.controller;

import com.baseball.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @Autowired
    private UserService userService;

    //로그인 전 메인페이지
    //rankingController로 이동

    //로그인 후 메인페이지
    //rankingController로 이동

    //회원가입 페이지
    @GetMapping("/signUp")
    public String signUp() {
        return "signUp";
    }

    //로그인 페이지
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    //응원하는 팀 변경페이지
    @GetMapping("/changeMyTeam")
    public String changeMyTeam() {
        return "changeMyTeam";
    }

    //회원정보변경 페이지
    @GetMapping("/changeMyInfo")
    public String changeMyInfo(Model model) {
        return "changeMyInfo";
    }

    //비밀번호 변경 페이지
    @GetMapping("/changePassword")
    public String changePassword() { return "changePassword"; }

    //야구일기
    @GetMapping("/calendar")
    public String diary() {
        return "calendar";
    }
}
