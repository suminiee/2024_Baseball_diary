package com.baseball.crawling;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/schedule")
    public String getTodaySchedule(Model model) {
        List<Schedule> scheduleList = scheduleService.scrapeTodayGames();
        model.addAttribute("schedule", scheduleList);
        return "schedule";
    }
}
