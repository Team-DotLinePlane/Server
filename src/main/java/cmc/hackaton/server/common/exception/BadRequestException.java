package cmc.hackaton.server.common.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends ApplicationException {

    public BadRequestException() {
        super(HttpStatus.BAD_REQUEST);
    }

    public BadRequestException(String optionalMessage) {
        super(HttpStatus.BAD_REQUEST, optionalMessage);
    }
}
