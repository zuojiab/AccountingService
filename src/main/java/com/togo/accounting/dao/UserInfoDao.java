package com.togo.accounting.dao;

import com.togo.accounting.model.persistence.UserInfo;

import org.springframework.stereotype.Repository;

/**
 * userInfoDao interface
 * create by crashLab.
 */
@Repository
public interface UserInfoDao {
    UserInfo getUserInfoByUserId(Long userId);
}
