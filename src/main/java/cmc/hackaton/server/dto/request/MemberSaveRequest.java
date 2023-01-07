package cmc.hackaton.server.dto.request;

import cmc.hackaton.server.dto.MemberDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor  // @RequestBody에서 쓰려면 default constructor가 있어야 한다.
@Getter
public class MemberSaveRequest {
    private String token;

    public static MemberSaveRequest of(String token) {
        return new MemberSaveRequest(token);
    }

    public MemberDto toDto() {
        return MemberDto.of(
                token,
                "익명"
        );
    }
}
