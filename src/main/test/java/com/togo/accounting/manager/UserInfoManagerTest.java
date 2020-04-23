package com.togo.accounting.manager;

import com.togo.accounting.converter.p2c.UserInfoP2CConverter;
import com.togo.accounting.dao.UserInfoDao;
import com.togo.accounting.exception.ResourceNotFoundException;
import com.togo.accounting.model.persistence.UserInfo;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * UserInfoManager Test
 * create by crashLab on 2020/04/22.
 **/
public class UserInfoManagerTest {


    private UserInfoManager userInfoManager;

    @Mock
    private UserInfoDao userInfoDao;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
        userInfoManager = new UserInfoManagerImpl(userInfoDao, new UserInfoP2CConverter());
    }


    @Test
    void testGetUserInfoByUserId(){
        //Arrange
        val userId = 1L;
        val username = "admin";
        val password = "admin";
        UserInfo userInfo = UserInfo.builder()
                                    .id(userId)
                                    .username(username)
                                    .password(password)
                                    .build();

        doReturn(userInfo).when(userInfoDao).getUserInfoByUserId(userId);

        //Act
        val result = userInfoManager.getUserInfoByUserId(userId);

        //Assert
        assertThat(result).isNotNull()
                          .hasFieldOrPropertyWithValue("id",userId)
                          .hasFieldOrPropertyWithValue("username",username)
                          .hasFieldOrPropertyWithValue("password",password);

        verify(userInfoDao).getUserInfoByUserId(eq(userId));

    }

    @Test
    public void testGetUserInfoByInvalidUserId(){
        val userId = -1L;

        doReturn(null).when(userInfoDao).getUserInfoByUserId(userId);

        assertThrows(ResourceNotFoundException.class, () -> userInfoManager.getUserInfoByUserId(userId));

        verify(userInfoDao).getUserInfoByUserId(eq(userId));

    }

}
