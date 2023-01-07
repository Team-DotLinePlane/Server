package cmc.hackaton.server.dto;

import cmc.hackaton.server.entity.Member;
import cmc.hackaton.server.entity.Team;
import cmc.hackaton.server.entity.TeamMember;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class TeamWithMembersDto {

    private String teamName;

    private String teamCode;

    private LocalTime mealTime;

    private Boolean isAlarmActive;

    private List<MemberDto> memberDtos;

    public static TeamWithMembersDto of(String teamName, String teamCode, LocalTime mealTime, Boolean isAlarmActive, List<MemberDto> memberDtos) {
        return new TeamWithMembersDto(teamName, teamCode, mealTime, isAlarmActive, memberDtos);
    }

    public static TeamWithMembersDto from(Team team) {
        return new TeamWithMembersDto(
                team.getTeamName(),
                team.getTeamCode(),
                team.getMealTime(),
                team.getIsAlarmActive(),
                team.getTeamMembers().stream()
                        .map(TeamMember::getMember)
                        .map(MemberDto::from)
                        .collect(Collectors.toList())
        );
    }

    public Team toEntity() {
        return Team.builder()
                .teamName(teamName)
                .teamCode(teamCode)
                .build();
    }
}
