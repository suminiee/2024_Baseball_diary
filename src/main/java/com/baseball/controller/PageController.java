package com.baseball.controller;

import com.baseball.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
        // 모델에 기본 속성 추가
        model.addAttribute("date", date);
        model.addAttribute("lineUp", new ArrayList<>()); // 빈 배열로 초기화
        model.addAttribute("game", new HashMap<>()); // 빈 맵으로 초기화
        model.addAttribute("mvp", ""); // 빈 문자열로 초기화
        return "diaryWrite";
    }

    // 일기 조회 페이지로 이동
    @GetMapping("/diary/view/{date}")
    public String viewDiary() {

        return "diaryView";
    }

    //리뷰 조회 페이지(메인)
    @GetMapping("/stadiumReview")
    public String viewReview() {
        return "stadiumReview";
    }

    //리뷰 작성 페이지
    @GetMapping("/stadiumReview/add")
    public String addReview() {
        return "stadiumReviewAdd";
    }

    //내가 쓴 리뷰 페이지
    @GetMapping("/stadiumReview/myReview")
    public String myReview() {
        return "stadiumReviewMyReview";
    }

    @GetMapping("/stadiumReview/test")
    public String viewReviewDetial() {
        // 필요한 로직을 추가합니다.
        return "stadiumReviewDetail";
    }
}
