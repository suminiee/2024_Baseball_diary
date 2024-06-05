package com.baseball.crawling;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecordService {
    // 다승 순위 가져오기
    public List<PitchingRecord> scrapeWins() {
        List<PitchingRecord> winDataList = new ArrayList<>();
        try {
            Document doc = Jsoup.connect("https://sports.news.naver.com/kbaseball/record/index?category=kbo").get();
            Elements rows = doc.select("#_pitcher_win .tb_kbo tbody tr");

            for (Element row : rows) {
                String rank = row.select("th").text();
                Elements tds = row.select("td");
                if (tds.size() >= 4) {
                    String team = tds.get(1).text();
                    String player = tds.get(2).text();
                    String value = tds.get(3).text();
                    PitchingRecord record = PitchingRecord.builder()
                            .rank(rank)
                            .team(team)
                            .player(player)
                            .value(value)
                            .build();
                    winDataList.add(record);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return winDataList;
    }

    // 평균자책 순위 가져오기
    public List<PitchingRecord> scrapeEra() {
        List<PitchingRecord> eraDataList = new ArrayList<>();
        try {
            Document doc = Jsoup.connect("https://sports.news.naver.com/kbaseball/record/index?category=kbo").get();
            Elements rows = doc.select("#_pitcher_era .tb_kbo tbody tr");

            for (Element row : rows) {
                String rank = row.select("th").text();
                Elements tds = row.select("td");
                if (tds.size() >= 4) {
                    String team = tds.get(1).text();
                    String player = tds.get(2).text();
                    String value = tds.get(3).text();
                    PitchingRecord record = PitchingRecord.builder()
                            .rank(rank)
                            .team(team)
                            .player(player)
                            .value(value)
                            .build();
                    eraDataList.add(record);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return eraDataList;
    }

    // 타율 순위 가져오기
    public List<BattingRecord> scrapeBattingAvg() {
        List<BattingRecord> battingAvgDataList = new ArrayList<>();
        try {
            Document doc = Jsoup.connect("https://sports.news.naver.com/kbaseball/record/index?category=kbo").get();
            Elements rows = doc.select("#_batter_avg .tb_kbo tbody tr");

            for (Element row : rows) {
                String rank = row.select("th").text();
                Elements tds = row.select("td");
                if (tds.size() >= 4) {
                    String team = tds.get(1).text();
                    String player = tds.get(2).text();
                    String value = tds.get(3).text();
                    BattingRecord record = BattingRecord.builder()
                            .rank(rank)
                            .team(team)
                            .player(player)
                            .value(value)
                            .build();
                    battingAvgDataList.add(record);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return battingAvgDataList;
    }

    // 홈런 순위 가져오기
    public List<BattingRecord> scrapeHomeRuns() {
        List<BattingRecord> homeRunDataList = new ArrayList<>();
        try {
            Document doc = Jsoup.connect("https://sports.news.naver.com/kbaseball/record/index?category=kbo").get();
            Elements rows = doc.select("#_batter_hr .tb_kbo tbody tr");

            for (Element row : rows) {
                String rank = row.select("th").text();
                Elements tds = row.select("td");
                if (tds.size() >= 4) {
                    String team = tds.get(1).text();
                    String player = tds.get(2).text();
                    String value = tds.get(3).text();
                    BattingRecord record = BattingRecord.builder()
                            .rank(rank)
                            .team(team)
                            .player(player)
                            .value(value)
                            .build();
                    homeRunDataList.add(record);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return homeRunDataList;
    }
}
