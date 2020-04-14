package com.togo.accounting.exception;

import lombok.Data;

/**
 * @Description service exception.
 * @create 2020-04-13 04:01
 **/
@Data
public class ServiceException extends RuntimeException {
    private int statusCode;
    private String errorCode; //biz error code
    private ServiceException.errorType errorType;//client,service,unknown

    public enum errorType{
        Client,
        Service,
        Unknown
    }

    public ServiceException(String message) {
        super(message);
    }
}
