package cmc.hackaton.server.dto;

import cmc.hackaton.server.entity.Team;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class TeamDto {

    private String teamName;

    private String teamCode;

    private LocalTime mealTime;

    private Boolean isAlarmActive;

    public static TeamDto of(String teamName) {
        return new TeamDto(teamName, null, null, null);
    }

    public static TeamDto of(String teamName, String teamCode, LocalTime mealTime, Boolean isAlarmActive) {
        return new TeamDto(teamName, teamCode, mealTime, isAlarmActive);
    }



    public static TeamDto from(Team team) {
        return new TeamDto(
                team.getTeamName(),
                team.getTeamCode(),
                team.getMealTime(),
                team.getIsAlarmActive()
        );
    }

    public Team toEntity(String teamCode) {
        return Team.builder()
                .teamName(teamName)
                .teamCode(teamCode)
                .build();
    }
}