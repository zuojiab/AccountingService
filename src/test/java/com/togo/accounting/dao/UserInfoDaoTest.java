package com.togo.accounting.dao;

import com.togo.accounting.dao.mapper.UserInfoMapper;
import com.togo.accounting.model.persistence.UserInfo;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

/**
 * UserInfoDao Test
 * create by crashLab on 2020/04/27.
 **/
@ExtendWith(MockitoExtension.class)
public class UserInfoDaoTest {
    @Mock
    private UserInfoMapper userInfoMapper;

    private UserInfoDao userInfoDao;

    @BeforeEach
    void setup(){
        userInfoDao = new UserInfoDaoImpl(userInfoMapper);
    }

    @Test
    void testGetUserInfoById(){
        val userId = 100L;
        val username = "admin";
        val password = "admin";
        UserInfo userInfo = UserInfo.builder()
                                    .id(userId)
                                    .username(username)
                                    .password(password)
                                    .build();
        doReturn(userInfo).when(userInfoMapper).getUserInfoByUserId(userId);
        UserInfo result = userInfoDao.getUserInfoByUserId(userId);
        assertEquals(userInfo,result);
        verify(userInfoMapper).getUserInfoByUserId(userId);
    }
}
