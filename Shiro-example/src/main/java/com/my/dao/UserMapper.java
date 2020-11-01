package com.my.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.my.pojo.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
    @Select("select password from user where user_name = #{userName}")
    String getPassword(String userName);

    @Select("select role from user where user_name = #{userName}")
    String getRole(String userName);
}
