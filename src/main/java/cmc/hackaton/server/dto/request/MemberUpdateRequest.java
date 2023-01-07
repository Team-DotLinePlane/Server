package cmc.hackaton.server.dto.request;

import cmc.hackaton.server.dto.MemberDto;
import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor  // @RequestBody에서 쓰려면 default constructor가 있어야 한다.
@Getter
public class MemberUpdateRequest {

    private String token;
    private String nickname;

    public static MemberUpdateRequest of(String token, String nickname) {
        return new MemberUpdateRequest(token, nickname);
    }

    public MemberDto toDto() {
        return MemberDto.of(
                token,
                nickname
        );
    }
}
