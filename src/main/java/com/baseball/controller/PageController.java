package com.baseball.controller;

import com.baseball.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
    @GetMapping("/diary")
    public String diary() {
        return "calendar";
    }

    @GetMapping("/diary/write/{date}")
    public String writeDiary(@PathVariable String date, Model model) {
        // 필요한 로직을 추가합니다.
        model.addAttribute("date", new SimpleDateFormat("yyyy.MM.dd").format(new Date()));
        model.addAttribute("lineUp", new ArrayList<>()); // 빈 배열로 초기화
        model.addAttribute("homeScores", new ArrayList<>()); // 빈 배열로 초기화
        model.addAttribute("awayScores", new ArrayList<>()); // 빈 배열로 초기화
        model.addAttribute("homeTeam", ""); // 빈 문자열로 초기화
        model.addAttribute("awayTeam", ""); // 빈 문자열로 초기화
        return "diaryWrite";
    }

    // 일기 조회 페이지로 이동
    @GetMapping("/diary/view/{date}")
    public String viewDiary(@PathVariable String date, Model model) {
        // 필요한 로직을 추가합니다.
        model.addAttribute("date", date);
        return "diaryView";
    }
}
