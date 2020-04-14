package com.togo.accounting.manager;


import com.togo.accounting.converter.p2c.UserInfoP2CConverter;
import com.togo.accounting.exception.ResourceNotFoundException;
import com.togo.accounting.model.common.UserInfo;
import com.togo.accounting.dao.UserInfoDao;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class UserInfoManagerImpl implements UserInfoManager {

    private final UserInfoDao userInfoDao;
    private final UserInfoP2CConverter userInfoP2CConverter;

    @Autowired
    public UserInfoManagerImpl(final UserInfoDao userInfoDao,
                               final UserInfoP2CConverter userInfoP2CConverter) {
        this.userInfoDao = userInfoDao;
        this.userInfoP2CConverter = userInfoP2CConverter;
    }
    @Override
    public UserInfo getUserInfoByUserId(Long userId) {
        val userInfo = Optional
                .ofNullable(userInfoDao.getUserInfoByUserId(userId))
                .orElseThrow(() -> (new ResourceNotFoundException(
                        String.format("User %s was not found",userId))));
        /**
         * 这里只解决了可以自定义异常 但是返回的是
         * {
         *     "timestamp": "2020-04-12T20:20:49.170+0000",
         *     "status": 500,
         *     "error": "Internal Server Error",
         *     "message": "User 1000 was not found",
         *     "path": "/v1.0/users/1000"
         * }
         * 说明系统认为的是service的问题 但是userId是合法的
         * 为什么1就可以找到 而1000就找不到
         * {
         *     "id": 1,
         *     "username": "admin",
         *     "password": null
         * }
         * 说明仍然需要改造exception
         * spring关于这一点
         * 在ResourceNotFoundException上加入
         * @ResponseStatus(code = HttpStatus.NOT_FOUND)
         *
         * {
         *     "timestamp": "2020-04-12T20:24:29.990+0000",
         *     "status": 404,
         *     "error": "Not Found",
         *     "message": "User 1000 was not found",
         *     "path": "/v1.0/users/1000"
         * }
         * 因为code是default HttpStatus.INTERNAL_SERVER_ERROR
         *
         * 我们也可以不使用spring的exception 通过自定义的方式 来实现*/
        return userInfoP2CConverter.convert(userInfo);
    }


    }
