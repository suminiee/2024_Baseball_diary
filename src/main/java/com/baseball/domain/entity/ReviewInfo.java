package com.baseball.domain.entity;

import com.baseball.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.w3c.dom.Text;

import java.util.Date;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reviewInfo")
public class ReviewInfo extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long reviewId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userInfo", nullable = false)
    private UserInfo userId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "teamInfo", nullable = false)
    private TeamInfo teamId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column
    private String imageAddress;

    @Column(nullable = false)
    private String visitedAt;


}
