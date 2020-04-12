package com.togo.accounting.manager;


import com.togo.accounting.converter.p2c.UserInfoP2CConverter;
import com.togo.accounting.model.common.UserInfo;
import com.togo.accounting.dao.UserInfoDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


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
        com.togo.accounting.model.persistence.UserInfo userInfo = userInfoDao.getUserInfoByUserId(userId);
        return userInfoP2CConverter.convert(userInfo);
    }


    }
