package com.togo.accounting.dao;

import com.togo.accounting.dao.mapper.UserInfoMapper;
import com.togo.accounting.model.persistence.UserInfo;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * userInfoDao implement
 * create by crashLab.
 */
@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserInfoDaoImpl implements UserInfoDao {

    private final UserInfoMapper userInfoMapper;

    @Override
    public UserInfo getUserInfoByUserId(Long userId) {
        return userInfoMapper.getUserInfoByUserId(userId);
    }

    @Override
    public UserInfo getUserInfoByUserName(String username) {
        return userInfoMapper.getUserInfoByUserName(username);
    }

    @Override
    public void createUser(UserInfo userInfo) {
        userInfoMapper.createNewUser(userInfo);
    }


}
