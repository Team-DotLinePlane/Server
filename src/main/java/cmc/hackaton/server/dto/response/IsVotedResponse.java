package cmc.hackaton.server.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class IsVotedResponse {

    private boolean isVoted;

    public static IsVotedResponse of(boolean isVoted) {
        return new IsVotedResponse(isVoted);
    }
}
