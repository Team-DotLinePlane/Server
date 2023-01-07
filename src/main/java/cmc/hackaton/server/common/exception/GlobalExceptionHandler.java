package cmc.hackaton.server.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResponse> applicationExceptionHandle(ApplicationException e) {
        log.info("Application Exception: {}", e.toString());
        return ResponseEntity.status(e.getHttpStatus())
                .body(new ErrorResponse(e.getErrorCode(), e.getErrorMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodArgumentNotValidExceptionHandle(MethodArgumentNotValidException e) {
        log.info("MethodArgumentNotValidException: {}", e.toString());
        String errorCode = ExceptionType.METHOD_ARGUMENT_NOT_VALID_EXCEPTION.getErrorCode();
        String errorMessage = e.getAllErrors().get(0).getDefaultMessage();
        return ResponseEntity.badRequest()
                .body(new ErrorResponse(errorCode, errorMessage));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> runtimeExceptionHandle(Exception e) {
        StackTraceElement[] stackTrace = e.getStackTrace();
        log.error("UnHandled Exception: {}\n" + "{}:{}:{}", e,
                stackTrace[0].getClassName(), stackTrace[0].getMethodName(), stackTrace[0].getLineNumber());
        String errorCode = ExceptionType.UNHANDLED_EXCEPTION.getErrorCode();
        String errorMessage = ExceptionType.UNHANDLED_EXCEPTION.getErrorMessage();
        return ResponseEntity.internalServerError()
                .body(new ErrorResponse(errorCode, errorMessage));
    }
}
