package com.togo.accounting.dao.mapper;

import com.togo.accounting.model.persistence.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Description
 * @create 2020-04-12 19:02
 **/
@Mapper
public interface UserInfoMapper {

    @Select("SELECT id,username,password,create_time,update_time FROM user_info WHERE id = #{id}")
    UserInfo getUserInfoByUserId(@Param("id") Long id);
}
