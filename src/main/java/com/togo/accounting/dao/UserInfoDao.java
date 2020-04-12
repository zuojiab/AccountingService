package com.togo.accounting.dao;

import com.togo.accounting.model.persistence.UserInfo;
import org.springframework.stereotype.Repository;

/**
 * @Description
 * @create 2020-04-12 18:29
 **/
@Repository
public interface UserInfoDao {
    UserInfo getUserInfoByUserId(Long userId);
}
