package com.togo.accounting.exception;

import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * GlobalExceptionHandler
 * create by crashLab.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ServiceException.class)
    ResponseEntity<?> handleServiceException(ServiceException ex) {
        val errorResponse = ErrorResponse.builder()
                                         .code(ex.getErrorCode())
                                         .errorType(ex.getErrorType())
                                         .message(ex.getMessage())
                                         .statusCode(ex.getStatusCode())
                                         .build();
        return ResponseEntity.status(ex.getStatusCode() != 0 ? ex.getStatusCode()
                                                             : HttpStatus.INTERNAL_SERVER_ERROR.value())
                             .body(errorResponse);
    }
}
