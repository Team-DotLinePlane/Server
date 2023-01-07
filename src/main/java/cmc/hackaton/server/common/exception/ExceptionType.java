package cmc.hackaton.server.common.exception;

import cmc.hackaton.server.common.exception.member.MemberNotFoundException;
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
//    EMAIL_FORMAT_EXCEPTION("2002", "이메일 형식이 맞지 않습니다.", EmailFormatException.class),
//    EMAIL_DUPLICATE_EXCEPTION("2003", "이미 사용중인 이메일입니다.", EmailDuplicateException.class),
//
//    NICKNAME_NULL_OR_EMPTY_EXCEPTION("2004", "회원의 이름은 한 글자 이상이어야 합니다.", NicknameNullOrEmptyException.class),
//    NICKNAME_TOO_LONG_EXCEPTION("2005", "회원의 이름은 255자를 초과할 수 없습니다.", NicknameTooLongException.class),
//    NICKNAME_DUPLICATE_EXCEPTION("2006", "이미 사용중인 이름입니다.", NicknameDuplicateException.class),
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
