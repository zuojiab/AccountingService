package com.togo.accounting.exception;

/**
 * create by crashLab on 2020/05/16.
 **/
public enum BizErrorCode {
     INVALID_PARAMETER("INVALID_PARAMETER"),
     RESOURCE_NOT_FOUND("RESOURCE_NOT_FOUND"),
     NO_AUTHORIZED("NO_AUTHORIZED"),
    INCORRECT_CREDENTIALS("INCORRECT_CREDENTIALS");

    private String message;
    BizErrorCode(String message) {
    }
}
