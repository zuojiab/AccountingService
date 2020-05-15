package com.togo.accounting.manager;


import com.togo.accounting.model.common.UserInfo;



public interface UserInfoManager {
    /**
      *  Get user information by user id.
      * @param  userId the specific user id.
      */
    UserInfo getUserInfoByUserId(Long userId);

    /**
     *  Get user information by user name.
     * @param  username the specific user username.
     */
    UserInfo getUserInfoByUserName(String username);

    /**
     *  login with username and password.
     * @param  username the specific user name.
     * @param  password the specific password.
     */
    void login(String username,String password);

    UserInfo register(String username,String password);
}
