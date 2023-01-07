package cmc.hackaton.server.dto.request;

import cmc.hackaton.server.dto.MemberDto;
import cmc.hackaton.server.dto.TeamDto;
import cmc.hackaton.server.dto.TeamWithMembersDto;
import cmc.hackaton.server.entity.Member;

public class TeamJoinRequest {

    private String token;
    private String teamCode;

    public static TeamJoinRequest of(String token, String teamCode) {
        return new TeamJoinRequest(token, teamCode);
    }

    public TeamWithMembersDto toDto() {
        return TeamWithMembersDto.of(
                token,
                teamCode
        );
    }
}
