package cmc.hackaton.server.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApplicationException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String errorMessage;

    public ApplicationException(HttpStatus httpStatus) {
        ExceptionType exceptionType = ExceptionType.from(this.getClass());
        this.httpStatus = httpStatus;
        this.errorCode = exceptionType.getErrorCode();
        this.errorMessage = exceptionType.getErrorMessage();
    }

    public ApplicationException(HttpStatus httpStatus, String optionalMessage) {
        ExceptionType exceptionType = ExceptionType.from(this.getClass());
        this.httpStatus = httpStatus;
        this.errorCode = exceptionType.getErrorCode();
        this.errorMessage = exceptionType.getErrorMessage() + optionalMessage;
    }
}
