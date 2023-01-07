package cmc.hackaton.server.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
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
