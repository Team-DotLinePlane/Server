package cmc.hackaton.server.dto.response;

import cmc.hackaton.server.dto.MemberDto;
import cmc.hackaton.server.dto.TeamDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class TeamResponse {

    private String teamName;

    private String teamCode;

    private LocalTime mealTime;

    private Boolean isAlarmActive;

    public static TeamResponse of(String teamName, String teamCode, LocalTime mealTime, Boolean isAlarmActive) {
        return new TeamResponse(teamName,teamCode, mealTime, isAlarmActive);
    }

    public static TeamResponse from(TeamDto dto) {
        return new TeamResponse(
                dto.getTeamName(), dto.getTeamCode(), dto.getMealTime(), dto.getIsAlarmActive()
        );
    }
}
