package com.togo.accounting.converter.c2s;

import com.togo.accounting.model.common.UserInfo;
import lombok.val;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

/**
 * create by crashLab on 2020/04/27.
 **/
public class UserInfoC2SConverterTest {
    private UserInfoC2SConverter userInfoC2SConverter = new UserInfoC2SConverter();

    @Test
    void testDoForward(){
        val userId = 1L;
        val username = "admin";
        val password = "admin";
        val userInfo = UserInfo.builder()
                               .id(userId)
                               .username(username)
                               .password(password)
                               .build();

        //Act
        val userInfoInCommon = userInfoC2SConverter.convert(userInfo);

        //Assert
        assertThat(userInfoInCommon).isNotNull()
                                         .hasFieldOrPropertyWithValue("id",userId)
                                         .hasFieldOrPropertyWithValue("username",username)
                                         .hasFieldOrPropertyWithValue("password",null);
    }

    @Test
    void testDoBackward(){
        val userId = 1L;
        val username = "admin";
        val password = "admin";
        com.togo.accounting.model.service.UserInfo userInfoInService = com.togo.accounting.model.service.UserInfo
                                                                                                       .builder()
                                                                                                       .id(userId)
                                                                                                       .username(username)
                                                                                                       .password(password)
                                                                                                       .build();

        val result = userInfoC2SConverter.reverse().convert(userInfoInService);

        assertThat(result).isNotNull()
                                    .hasFieldOrPropertyWithValue("id",userId)
                                    .hasFieldOrPropertyWithValue("username",username)
                                    .hasFieldOrPropertyWithValue("password",password);

    }
}
