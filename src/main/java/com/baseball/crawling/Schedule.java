package com.baseball.crawling;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Schedule {
    private String time;
    private String teams;
    private String result;
}
