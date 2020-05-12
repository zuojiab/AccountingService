package com.togo.accounting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 *
 * create by crashLab.
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestException extends ServiceException {
    /**
     *
     * create by crashLab.
     */
    public BadRequestException(String message) {
        super(message);
        this.setStatusCode(HttpStatus.BAD_REQUEST.value());
        this.setErrorCode("USER_INFO_INVALID");
        this.setErrorType(ErrorType.Client);
    }
}
