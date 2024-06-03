package com.baseball.crawling;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RankingController {
    private final RankingService rankingService;

    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    //로그인 전 메인페이지
    @GetMapping("/")
    public String ranking(Model model) {
        List<Ranking> rankingList = rankingService.scrapeRank();
        model.addAttribute("rankings", rankingList);

        return "main";
    }

    //로그인 후 메인페이지
    @GetMapping("/main")
    public String loginRanking(Model model) {
        List<Ranking> rankingList = rankingService.scrapeRank();
        model.addAttribute("rankings", rankingList);

        return "afterLoginMain";
    }
}
