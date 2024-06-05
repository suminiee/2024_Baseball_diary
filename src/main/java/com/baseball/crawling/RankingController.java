package com.baseball.crawling;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RankingController {
    private final RankingService rankingService;
    private final RecordService recordService;

    public RankingController(RankingService rankingService, RecordService recordService) {
        this.rankingService = rankingService;
        this.recordService = recordService;
    }

    //로그인 전 메인페이지
    @GetMapping("/")
    public String ranking(Model model) {
        List<Ranking> rankingList = rankingService.scrapeRank();
        model.addAttribute("rankings", rankingList);

        List<PitchingRecord> winRecords = recordService.scrapeWins();
        List<PitchingRecord> eraRecords = recordService.scrapeEra();
        List<BattingRecord> battingAvgRecords = recordService.scrapeBattingAvg();
        List<BattingRecord> homeRunRecords = recordService.scrapeHomeRuns();

        model.addAttribute("winRecords", winRecords);
        model.addAttribute("eraRecords", eraRecords);
        model.addAttribute("battingAvgRecords", battingAvgRecords);
        model.addAttribute("homeRunRecords", homeRunRecords);

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
