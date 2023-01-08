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

    private Long teamId;

    private String teamName;

    private String teamCode;

    private LocalTime mealTime;

    private Boolean isAlarmActive;

    private int numOfMembers;
    private Boolean isVoteProgress;

    public static TeamResponse of(Long teamId, String teamName, String teamCode, LocalTime mealTime, Boolean isAlarmActive, int numOfMembers, boolean isVoteProgress) {
        return new TeamResponse(teamId, teamName,teamCode, mealTime, isAlarmActive, numOfMembers, isVoteProgress);
    }

    public static TeamResponse from(TeamDto dto) {
        return new TeamResponse(
                dto.getTeamId(),
                dto.getTeamName(),
                dto.getTeamCode(),
                dto.getMealTime(),
                dto.getIsAlarmActive(),
                dto.getNumOfMembers(),
                dto.getIsVoteProgress()
        );
    }
}
