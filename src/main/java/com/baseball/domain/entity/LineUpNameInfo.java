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
@Table(name = "lineUpNameInfo")
public class LineUpNameInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long lineUpNameId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "diaryInfo", nullable = true)
    private UserInfo diaryId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userInfo", nullable = false)
    private UserInfo loginId;

    @Column
    private String hitter1;
    @Column
    private String hitter2;
    @Column
    private String hitter3;
    @Column
    private String hitter4;
    @Column
    private String hitter5;
    @Column
    private String hitter6;
    @Column
    private String hitter7;
    @Column
    private String hitter8;
    @Column
    private String hitter9;
    @Column
    private String pitcher;

}
