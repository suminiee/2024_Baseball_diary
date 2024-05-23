package com.baseball.domain.entity;

import com.baseball.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "diaryInfo")
public class DiaryInfo extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long diaryId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userInfo", nullable = false)
    private UserInfo userId;

    @Column(nullable = false)
    private String stadium;

    @Column(nullable = false)
    private String watch;

    @Column(nullable = false)
    private String gameDate;

    @Column(nullable = false)
    private String time;

    @Column(nullable = false)
    private String home;

    @Column(nullable = false)
    private String away;

    @Column
    private String mvp;

    @Column
    private Long homeScore;

    @Column
    private Long awayScore;

}
