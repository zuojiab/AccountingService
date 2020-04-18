package com.togo.accounting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Description
 * @create 2020-04-18 22:32
 **/
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestException extends ServiceException{
    public BadRequestException(String message) {
        super(message);
        this.setStatusCode(HttpStatus.BAD_REQUEST.value());
        this.setErrorCode("USER_INFO_INVALID");
    }
}
