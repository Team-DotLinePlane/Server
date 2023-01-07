package cmc.hackaton.server.dto;

import cmc.hackaton.server.entity.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class MemberDto {

    private String token;
    private String nickname;

    public static MemberDto of(String token, String nickname) {
        return new MemberDto(token, nickname);
    }

    public static MemberDto from(Member member) {
        return new MemberDto(
                member.getToken(),
                member.getNickname()
        );
    }

    public Member toEntity() {
        return Member.builder()
                .token(token)
                .nickname(nickname)
                .build();
    }
}
