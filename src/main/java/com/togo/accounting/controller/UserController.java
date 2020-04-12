package com.togo.accounting.controller;

import com.togo.accounting.converter.c2s.UserInfoC2SConverter;
import com.togo.accounting.manager.UserInfoManager;
import com.togo.accounting.model.service.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
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
    public UserInfo getUserInfoByUserId(@PathVariable("id") Long userId){
      return userInfoC2SConverter.convert(userInfoManager.getUserInfoByUserId(userId));
    }
}
