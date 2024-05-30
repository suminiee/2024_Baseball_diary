package com.baseball.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @GetMapping("/")
    public String index() {
        return "beforeLogin";
    }

    @GetMapping("/main")
    public String main() {
        return "afterLogin";
    }

    @GetMapping("/signUp")
    public String signUp() {
        return "signUp";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
