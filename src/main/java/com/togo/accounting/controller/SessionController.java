package com.togo.accounting.controller;

import com.togo.accounting.manager.UserInfoManager;


import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * create by crashLab on 2020/05/14.
 **/
@RestController
@RequestMapping("v1.0/session")
public class SessionController {

    private final UserInfoManager userInfoManager;

    @Autowired
    public SessionController(UserInfoManager userInfoManager) {
        this.userInfoManager = userInfoManager;
    }

    @PostMapping
    public String login(@PathParam("username") String username,
                        @PathParam("password") String password) {
        userInfoManager.login(username,password);
        return "success";
    }
}
