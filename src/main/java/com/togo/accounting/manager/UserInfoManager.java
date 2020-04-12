package com.togo.accounting.manager;


import com.togo.accounting.model.common.UserInfo;

public interface UserInfoManager {
    /**
      *  Get user information by user id.
      * @Param userId the specific user id.
      */
    UserInfo getUserInfoByUserId(Long userId);

}
