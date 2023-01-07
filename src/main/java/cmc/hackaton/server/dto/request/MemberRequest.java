package cmc.hackaton.server.dto.request;

import cmc.hackaton.server.dto.MemberDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class MemberRequest {

    private String token;

    public static MemberRequest of(String token) {
        return new MemberRequest(token);
    }

    public MemberDto toDto() {
        return MemberDto.of(
                token,
                "익명"
        );
    }
}
