package cmc.hackaton.server.common.exception;

import cmc.hackaton.server.common.exception.member.MemberNotFoundException;
import cmc.hackaton.server.common.exception.team.TeamNotFoundException;
import cmc.hackaton.server.common.exception.vote.VoteNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@AllArgsConstructor
@Getter
public enum ExceptionType {

    // Common
    UNHANDLED_EXCEPTION("0000", "알 수 없는 서버 에러가 발생했습니다."),
    METHOD_ARGUMENT_NOT_VALID_EXCEPTION("0001", "요청 데이터가 잘못되었습니다."),

    // Member
    MEMBER_NOT_FOUND_EXCEPTION("2001", "존재하지 않는 회원입니다.", MemberNotFoundException.class),

    // Team
    TEAM_NOT_FOUND_EXCEPTION("3001", "존재하지 않는 그룹입니다.", TeamNotFoundException.class),

    // Vote
    VOTE_NOT_FOUND_EXCEPTION("4001", "존재하지 않거나 종료된 투표입니다.", VoteNotFoundException.class),
    ;

    private final String errorCode;
    private final String errorMessage;
    private Class<? extends ApplicationException> type;

    ExceptionType(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public static ExceptionType from(Class<?> classType) {
        return Arrays.stream(values())
                .filter(it -> Objects.nonNull(it.type) && it.type.equals(classType))
                .findFirst()
                .orElse(UNHANDLED_EXCEPTION);
    }
}
