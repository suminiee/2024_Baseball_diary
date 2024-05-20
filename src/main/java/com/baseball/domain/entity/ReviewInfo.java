package com.baseball.domain.entity;

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
@Table(name = "reviewInfo")
public class ReviewInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long reviewId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userInfo", nullable = false)
    private UserInfo loginId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "teamInfo", nullable = false)
    private UserInfo teamId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column
    private String imageAddress;

    @Column(nullable = false)
    private Date visitedAt;
}
