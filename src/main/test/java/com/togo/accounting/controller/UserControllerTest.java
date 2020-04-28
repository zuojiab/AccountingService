package com.togo.accounting.controller;

import com.togo.accounting.converter.c2s.UserInfoC2SConverter;
import com.togo.accounting.exception.BadRequestException;
import com.togo.accounting.exception.GlobalExceptionHandler;
import com.togo.accounting.exception.ResourceNotFoundException;
import com.togo.accounting.manager.UserInfoManager;
import com.togo.accounting.model.common.UserInfo;
import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.naming.NamingEnumeration;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * create by crashLab on 2020/04/27.
 **/
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    private MockMvc mockMvc;


    @Mock
    private UserInfoManager userInfoManager;

    @Mock
    private UserInfoC2SConverter userInfoC2SConverter;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                                 .setControllerAdvice(new GlobalExceptionHandler())
                                 .build();
    }

    @AfterEach
    void teardown(){
        reset(userInfoManager);
        reset(userInfoC2SConverter);
    }
    @Test
    void testGetUserInfoByUserId() throws Exception{
        val userId = 1L;
        val username = "admin";
        val password = "admin";
        val userInfoInCommon = UserInfo.builder()
                               .id(userId)
                               .username(username)
                               .password(password)
                               .build();

        val userInfoInService = com.togo.accounting.model.service.UserInfo.builder()
                                                                          .id(userId)
                                                                          .username(username)
                                                                          .password(password)
                                                                          .build();
        doReturn(userInfoInCommon).when(userInfoManager).getUserInfoByUserId(anyLong());

        doReturn(userInfoInService).when(userInfoC2SConverter).convert(userInfoInCommon);




        mockMvc.perform(MockMvcRequestBuilders.get("/v1.0/users/"+userId))
               .andExpect(status().isOk())
               .andExpect(content().contentType("application/json"))
               .andExpect(content().string("{\"id\":1,\"username\":\"admin\",\"password\":\"admin\"}"));

        verify(userInfoManager).getUserInfoByUserId(userId);
        verify(userInfoC2SConverter).convert(userInfoInCommon);
    }

    @Test
    void testGetUserInfoByUserIdWithInvalidUserId() throws Exception{
        val userId = -100L;

        doThrow(new ResourceNotFoundException(String.format("User %s was not found",userId)))
                .when(userInfoManager)
                .getUserInfoByUserId(anyLong());

        mockMvc.perform(MockMvcRequestBuilders.get("/v1.0/users/"+userId))
               .andExpect(status().is4xxClientError())
               .andExpect(content().string("{\"code\":\"USER_INFO_INVALID\",\"errorType\":\"Client\",\"message\":\"The user id -100 is invalid.\",\"statusCode\":400}"));

        verify(userInfoManager,never()).getUserInfoByUserId(anyLong());


    }
}
