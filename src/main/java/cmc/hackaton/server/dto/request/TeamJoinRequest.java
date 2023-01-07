package cmc.hackaton.server.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class TeamJoinRequest {

    private String token;
    private String teamCode;

    public static TeamJoinRequest of(String token, String teamCode) {
        return new TeamJoinRequest(
                token,
                teamCode
        );
    }
}
