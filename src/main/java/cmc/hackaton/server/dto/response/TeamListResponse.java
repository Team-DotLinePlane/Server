package cmc.hackaton.server.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class TeamListResponse {

    private List<TeamResponse> teamResponses;

    public static TeamListResponse of(List<TeamResponse> teamResponses) {
        return new TeamListResponse(teamResponses);
    }
}
