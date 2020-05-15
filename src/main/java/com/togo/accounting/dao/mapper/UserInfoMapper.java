package com.togo.accounting.dao.mapper;

import com.togo.accounting.model.persistence.UserInfo;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * userInfo mapper
 * create by crashLab.
 */
@Mapper
public interface UserInfoMapper {

    @Select("SELECT id,username,password,create_time,update_time FROM user_info WHERE id = #{id}")
    UserInfo getUserInfoByUserId(@Param("id") Long id);

    @Select("SELECT id,username,password,salt,create_time,update_time FROM user_info WHERE username = #{username}")
    UserInfo getUserInfoByUserName(@Param("username") String username);

    @Insert("INSERT INTO user_info (username,password,salt,create_time) VALUES (#{username},#{password},#{salt},#{create_time}) ")
    void createNewUser(UserInfo userInfo);
}
