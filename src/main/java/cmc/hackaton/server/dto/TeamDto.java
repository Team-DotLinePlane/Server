package cmc.hackaton.server.dto;

import cmc.hackaton.server.entity.Team;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class TeamDto {

    private Long teamId;

    private String teamName;

    private String teamCode;

    private LocalTime mealTime;

    private Boolean isAlarmActive;
    private Integer numOfMembers;

    public static TeamDto of(String teamName) {
        return new TeamDto(null, teamName, null, null, null, null);
    }

    public static TeamDto from(Team team) {
        return new TeamDto(
                team.getId(),
                team.getTeamName(),
                team.getTeamCode(),
                team.getMealTime(),
                team.getIsAlarmActive(),
                team.getTeamMembers().size()
        );
    }

    public Team toEntity(String teamCode) {
        return Team.builder()
                .teamName(teamName)
                .teamCode(teamCode)
                .build();
    }
}
