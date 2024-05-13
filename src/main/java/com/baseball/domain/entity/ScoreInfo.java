package com.baseball.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "scoreInfo")
public class ScoreInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long scoreId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "diaryInfo", nullable = false)
    private UserInfo diaryId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userInfo", nullable = false)
    private UserInfo userId;

    @Column(nullable = false)
    private String type;

    @Column
    private Long inning1;
    @Column
    private Long inning2;
    @Column
    private Long inning3;
    @Column
    private Long inning4;
    @Column
    private Long inning5;
    @Column
    private Long inning6;
    @Column
    private Long inning7;
    @Column
    private Long inning8;
    @Column
    private Long inning9;
    @Column
    private Long inning10;
    @Column
    private Long inning11;
    @Column
    private Long inning12;
    @Column
    private Long run;
    @Column
    private Long hit;
    @Column
    private Long error;
    @Column
    private Long balls;

}
