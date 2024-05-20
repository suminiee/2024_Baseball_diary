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
@Table(name = "lineUpPositionInfo")
public class LineUpPositionInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long positionId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "diaryInfo", nullable = true)
    private UserInfo diaryId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userInfo", nullable = false)
    private UserInfo loginId;

    @Column
    private String position1;
    @Column
    private String position2;
    @Column
    private String position3;
    @Column
    private String position4;
    @Column
    private String position5;
    @Column
    private String position6;
    @Column
    private String position7;
    @Column
    private String position8;
    @Column
    private String position9;


}
