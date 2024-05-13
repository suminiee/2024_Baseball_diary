package com.baseball.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teamInfo")
public class TeamInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long teamId;

    @Column(nullable = false)
    private String teamName;

    @Column(nullable = false)
    private String stadium;

    @Column(nullable = false)
    private String teamLogo;

    @Column(nullable = false)
    private String youtubeLink;

    @Column(nullable = false)
    private String ticketLink;

    @Column(nullable = false)
    private String teamAddress;

    @Column
    private String coordinate;
}
