package com.togo.accounting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Description service resource not found Exception.
 * @create 2020-04-13 04:08
 **/
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends ServiceException{
    public ResourceNotFoundException(String message){
        super(message);
        this.setStatusCode(HttpStatus.NOT_FOUND.value());
    }
}
