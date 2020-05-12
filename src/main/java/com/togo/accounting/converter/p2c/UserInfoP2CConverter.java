package com.togo.accounting.converter.p2c;

import com.togo.accounting.model.persistence.UserInfo;

import com.google.common.base.Converter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class UserInfoP2CConverter extends Converter<UserInfo, com.togo.accounting.model.common.UserInfo> {

    @Override
    protected com.togo.accounting.model.common.UserInfo doForward(UserInfo userInfo) {
        return com.togo.accounting.model.common.UserInfo.builder()
                                                .id(userInfo.getId())
                                                .username(userInfo.getUsername())
                                                .password(userInfo.getPassword())
                                                .salt(userInfo.getSalt())
                                                .build();
    }

    @Override
    protected UserInfo doBackward(com.togo.accounting.model.common.UserInfo userInfo) {
        return UserInfo.builder()
                       .id(userInfo.getId())
                       .username(userInfo.getUsername())
                       .password(userInfo.getPassword())
                       .build();
    }
}
