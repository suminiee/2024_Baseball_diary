package com.baseball.crawling;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.baseball.crawling.Ranking;

@Service
public class RankingService {

    private static final String URL = "https://sports.news.naver.com/kbaseball/record/index?category=kbo";

    // KBO 리그 순위 가져오기 by Jsoup
    public List<Ranking> scrapeRank() {
        List<Ranking> rankingList = new ArrayList<>();
        try {
            // KBO 리그 순위 가져오기
            Document doc = Jsoup.connect(URL)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36")
                    .get();

            // select를 이용해서 tr들을 불러오기
            Elements baseballTeams = doc.select("#regularTeamRecordList_table > tr");

            // tr들의 반복문 돌리기
            for (Element baseballTeam : baseballTeams) {
                Element rank = baseballTeam.selectFirst("th"); // 순위
                Element title = baseballTeam.selectFirst("span:nth-child(2)"); // 팀 명
                Element gcnts = baseballTeam.selectFirst("td:nth-child(3)"); // 경기 수
                Element wpnt = baseballTeam.selectFirst("td:nth-child(4)"); // 승
                Element lpnt = baseballTeam.selectFirst("td:nth-child(5)"); // 패
                Element dpnt = baseballTeam.selectFirst("td:nth-child(6)"); // 무
                Element wnrt = baseballTeam.selectFirst("td:nth-child(7)"); // 승률
                Element wlpnt = baseballTeam.selectFirst("td:nth-child(8)"); // 게임차
                Element streak = baseballTeam.selectFirst("td:nth-child(9)"); // 연속
                Element onbase = baseballTeam.selectFirst("td:nth-child(10)"); // 출루율
                Element slugging = baseballTeam.selectFirst("td:nth-child(11)"); // 장타율
                Element lately = baseballTeam.selectFirst("td:nth-child(12)"); // 최근 10경기

                if (title != null) {
                    Ranking ranking = Ranking.builder()
                            .rank(rank != null ? rank.text() : "")
                            .team(title.text())
                            .gcnts(gcnts != null ? gcnts.text() : "")
                            .wpnt(wpnt != null ? wpnt.text() : "")
                            .lpnt(lpnt != null ? lpnt.text() : "")
                            .dpnt(dpnt != null ? dpnt.text() : "")
                            .wnrt(wnrt != null ? wnrt.text() : "")
                            .wlpnt(wlpnt != null ? wlpnt.text() : "")
                            .streak(streak != null ? streak.text() : "")
                            .onbase(onbase != null ? onbase.text() : "")
                            .slugging(slugging != null ? slugging.text() : "")
                            .lately(lately != null ? lately.text() : "")
                            .build();
                    rankingList.add(ranking);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rankingList;
    }
}
