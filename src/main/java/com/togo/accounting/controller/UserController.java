package com.togo.accounting.controller;

import com.sun.tools.corba.se.idl.ExceptionEntry;
import com.togo.accounting.converter.c2s.UserInfoC2SConverter;
import com.togo.accounting.exception.ErrorException;
import com.togo.accounting.exception.ResourceNotFoundException;
import com.togo.accounting.exception.ServiceException;
import com.togo.accounting.manager.UserInfoManager;
import com.togo.accounting.model.service.UserInfo;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Description
 * @create 2020-04-12 14:44
 **/
@RestController
@RequestMapping("v1.0/users")
public class UserController {
    private final UserInfoManager userInfoManager;
    private final UserInfoC2SConverter userInfoC2SConverter;

    @Autowired
    public UserController(UserInfoManager userInfoManager,
                          UserInfoC2SConverter userInfoC2SConverter) {
        this.userInfoManager = userInfoManager;
        this.userInfoC2SConverter = userInfoC2SConverter;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserInfoByUserId(@PathVariable("id") Long userId) {
        try {
            val userInfo = userInfoManager.getUserInfoByUserId(userId);
            return ResponseEntity.ok(userInfoC2SConverter.convert(userInfo));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(ErrorException.builder()
                                                     .code("NOT_FOUND")
                                                     .errorType(ServiceException.errorType.Client)
                                                     .statusCode(HttpStatus.NOT_FOUND.value())
                                                     .message(ex.getMessage())
                                                     .build());
                }

        /**
         * {
         *     "code": "NOT_FOUND",
         *     "errorType": "Client",
         *     "message": "User 1000 was not found",
         *     "statusCode": 404
         * }
         * */
    }
}
