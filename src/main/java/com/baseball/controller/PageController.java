package com.baseball.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    //로그인 전 메인페이지
    @GetMapping("/")
    public String main() {
        return "main";
    }

    //로그인 후 메인페이지
    @GetMapping("/main")
    public String loginMain() { return "afterLoginMain";}

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
}
