package cmc.hackaton.server.dto.response;

import cmc.hackaton.server.dto.MemberDto;
import cmc.hackaton.server.dto.TeamDto;
import cmc.hackaton.server.dto.TeamWithMembersDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class TeamWithMembersResponse {

    private Long teamId;
    private String teamName;

    private String teamCode;

    private LocalTime mealTime;

    private Boolean isAlarmActive;
    private List<MemberResponse> memberResponses;

    public static TeamWithMembersResponse of(Long teamId, String teamName, String teamCode, LocalTime mealTime, Boolean isAlarmActive, List<MemberResponse> memberDtos) {
        return new TeamWithMembersResponse(teamId, teamName,teamCode, mealTime, isAlarmActive, memberDtos);
    }

    public static TeamWithMembersResponse from(TeamWithMembersDto dto) {
        return new TeamWithMembersResponse(
                dto.getTeamId(),
                dto.getTeamName(),
                dto.getTeamCode(),
                dto.getMealTime(),
                dto.getIsAlarmActive(),
                dto.getMemberDtos().stream()
                        .map(MemberResponse::from)
                        .collect(Collectors.toList())
        );
    }
}
