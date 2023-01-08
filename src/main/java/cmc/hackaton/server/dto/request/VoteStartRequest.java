package cmc.hackaton.server.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor  // @RequestBody에서 쓰려면 default constructor가 있어야 한다.
@Getter
public class VoteStartRequest {

    private String memberToken;
    private Long teamId;

    public static VoteStartRequest of(String memberToken, Long teamId) {
        return new VoteStartRequest(memberToken, teamId);
    }
}
