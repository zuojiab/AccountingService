package com.togo.accounting.exception;

import lombok.Builder;
import lombok.Data;

/**
 * @Description
 * @create 2020-04-13 04:28
 **/
@Data
@Builder
public class ErrorResponse {
    private String code;
    private ServiceException.errorType errorType;
    private String message;
    private int statusCode;
}
