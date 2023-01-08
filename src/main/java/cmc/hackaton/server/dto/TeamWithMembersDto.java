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

    private Long teamId;
    private String teamName;

    private String teamCode;

    private LocalTime mealTime;

    private Boolean isAlarmActive;

    private Boolean isVoteProgress;
    private Long voteId;

    private List<MemberDto> memberDtos;

    public static TeamWithMembersDto of(Long teamId, String teamName, String teamCode, LocalTime mealTime, Boolean isAlarmActive, Boolean isVoteProgress, Long voteId, List<MemberDto> memberDtos) {
        return new TeamWithMembersDto(teamId, teamName, teamCode, mealTime, isAlarmActive, isVoteProgress, voteId, memberDtos);
    }

    public static TeamWithMembersDto from(Team team, Boolean isVoteProgress, Long voteId) {
        return new TeamWithMembersDto(
                team.getId(),
                team.getTeamName(),
                team.getTeamCode(),
                team.getMealTime(),
                team.getIsAlarmActive(),
                isVoteProgress,
                voteId,
                team.getTeamMembers().stream()
                        .map(TeamMember::getMember)
                        .map(MemberDto::from)
                        .collect(Collectors.toList())
        );
    }
}
