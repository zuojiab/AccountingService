package com.togo.accounting.controller;

import com.togo.accounting.converter.c2s.UserInfoC2SConverter;
import com.togo.accounting.exception.BadRequestException;
import com.togo.accounting.manager.UserInfoManager;
import com.togo.accounting.model.service.UserInfo;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;


/**
 * user controller
 * create by crashLab.
 */
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

    /**
     *
     * create by crashLab.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserInfo> getUserInfoByUserId(@PathVariable("id") Long userId) {
        if (userId <= 0L || userId == null) {
            throw new BadRequestException(String.format("The user id %s is invalid.",userId));
        }
        val userInfo = userInfoManager.getUserInfoByUserId(userId);
        return ResponseEntity.ok(Objects.requireNonNull(userInfoC2SConverter.convert(userInfo)));
    }
}
