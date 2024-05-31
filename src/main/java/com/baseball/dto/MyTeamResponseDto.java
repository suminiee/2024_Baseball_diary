package com.baseball.dto;

import com.baseball.domain.entity.TeamInfo;
import com.baseball.domain.entity.UserInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MyTeamResponseDto {
    private String myTeam;
    private String teamLogo;

    public MyTeamResponseDto(UserInfo userInfo, TeamInfo teamInfo) {
        this.myTeam = userInfo.getMyTeam();
        this.teamLogo = teamInfo.getTeamLogo();
    }
}
