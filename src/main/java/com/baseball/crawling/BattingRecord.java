package com.baseball.crawling;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class BattingRecord {
    private String rank;
    private String team;
    private String player;
    private String value;
}
