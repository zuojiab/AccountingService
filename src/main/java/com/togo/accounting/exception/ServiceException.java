package com.togo.accounting.exception;

import lombok.Data;

/**
 * service exception.
 * create by crashLab.
 */
@Data
public class ServiceException extends RuntimeException {
    private int statusCode;
    private String errorCode; //biz error code
    private ErrorType errorType;//client,service,unknown

    public enum ErrorType {
        Client,
        Service,
        Unknown
    }

    public ServiceException(String message) {
        super(message);
    }
}
