package com.baseball.crawling;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService {

    // 오늘의 경기 일정 가져오기 by Selenium and Jsoup
    public List<Schedule> scrapeTodayGames() {
        List<Schedule> gameDataList = new ArrayList<>();
        WebDriver driver = null;
        try {
            // WebDriverManager 설정
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless"); // 헤드리스 모드
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            driver = new ChromeDriver(options);

            // 오늘 날짜 구하기
            LocalDate today = LocalDate.now();
            String date = today.toString();

            // URL 설정
            String url = "https://m.sports.naver.com/kbaseball/schedule/index?date=" + date;

            // 페이지 로드
            driver.get(url);

            // 페이지 소스 가져오기
            String pageSource = driver.getPageSource();
            Document doc = Jsoup.parse(pageSource);

            // 경기 일정 크롤링
            Elements games = doc.select(".schedule_content > ul > li");

            for (Element game : games) {
                String time = game.select(".time").text();
                String teams = game.select(".team").text();
                String result = game.select(".result").text();

                Schedule gameData = new Schedule(time, teams, result);
                gameDataList.add(gameData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }

        return gameDataList;
    }
}
