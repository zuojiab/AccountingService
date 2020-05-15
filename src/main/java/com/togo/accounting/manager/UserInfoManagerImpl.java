package com.togo.accounting.manager;


import com.togo.accounting.converter.p2c.UserInfoP2CConverter;
import com.togo.accounting.dao.UserInfoDao;
import com.togo.accounting.exception.BadRequestException;
import com.togo.accounting.exception.ResourceNotFoundException;
import com.togo.accounting.model.common.UserInfo;

import lombok.val;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;


@Component
public class UserInfoManagerImpl implements UserInfoManager {

    public static final int HASH_ITERATIONS = 1000;
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
        return userInfoP2CConverter.convert(userInfo);
    }

    @Override
    public UserInfo getUserInfoByUserName(String username) {
        val userInfo = Optional
            .ofNullable(userInfoDao.getUserInfoByUserName(username))
            .orElseThrow(() -> (new ResourceNotFoundException(
                String.format("User name %s was not found",username))));
        return userInfoP2CConverter.convert(userInfo);
    }

    @Override
    public void login(String username, String password) {
        val subject = SecurityUtils.getSubject();

        val usernamePasswordToken = new UsernamePasswordToken(username,password);
        subject.login(usernamePasswordToken);
    }

    @Override
    public UserInfo register(String username,String password) {
        val userInfo = userInfoDao.getUserInfoByUserName(username);
        if (userInfo != null) {
            throw new BadRequestException(String.format("The user %s was registered."));
        }

        String salt = UUID.randomUUID().toString();
        String encryptedPassword = new Sha256Hash(password,salt, HASH_ITERATIONS).toBase64();

        val newUserInfo = com.togo.accounting.model.persistence.UserInfo.builder()
                                                                      .username(username)
                                                                      .password(encryptedPassword)
                                                                      .salt(salt)
                                                                      .create_time(LocalDate.now())
                                                                      .build();
        userInfoDao.createUser(newUserInfo);
        return userInfoP2CConverter.convert(newUserInfo);
    }


}
