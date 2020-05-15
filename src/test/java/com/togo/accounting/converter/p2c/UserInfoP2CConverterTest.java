package com.togo.accounting.converter.p2c;

import com.togo.accounting.model.persistence.UserInfo;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * create by crashLab on 2020/04/27.
 **/
public class UserInfoP2CConverterTest {
    private UserInfoP2CConverter userInfoP2CConverter = new UserInfoP2CConverter();

    @Test
    void testDoForward(){
        val userId = 1L;
        val username = "admin";
        val password = "admin";
        val createTime  = LocalDate.now();
        val userInfo = UserInfo.builder()
                                    .id(userId)
                                    .username(username)
                                    .password(password)
                                    .create_time(createTime)
                                    .build();

        //Act
        val userInfoInPersistence = userInfoP2CConverter.convert(userInfo);

        //Assert
        assertThat(userInfoInPersistence).isNotNull()
                                         .hasFieldOrPropertyWithValue("id",userId)
                                         .hasFieldOrPropertyWithValue("username",username)
                                         .hasFieldOrPropertyWithValue("password",password);
    }

    @Test
    void testDoBackward(){
        val userId = 1L;
        val username = "admin";
        val password = "admin";
        com.togo.accounting.model.common.UserInfo userInfoInCommon = com.togo.accounting.model.common.UserInfo
                                                                                                     .builder()
                                                                                                     .id(userId)
                                                                                                     .username(username)
                                                                                                     .password(password)
                                                                                                     .build();

        val result = userInfoP2CConverter.reverse().convert(userInfoInCommon);

        assertThat(userInfoInCommon).isNotNull()
                                         .hasFieldOrPropertyWithValue("id",userId)
                                         .hasFieldOrPropertyWithValue("username",username)
                                         .hasFieldOrPropertyWithValue("password",password);

    }

}
