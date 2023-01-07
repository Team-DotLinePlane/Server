package cmc.hackaton.server.dto.response;

import cmc.hackaton.server.dto.MemberDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class MemberResponse {

    private String nickname;

    public static MemberResponse of(String nickname) {
        return new MemberResponse(nickname);
    }

    public static MemberResponse from(MemberDto dto) {
        return new MemberResponse(
                dto.getNickname()
        );
    }
}
