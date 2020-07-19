package com.springboot.mybatis.dao;

import com.springboot.mybatis.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author w
 */
@Mapper
@Repository
public interface UerRegisterMapper {
    List<User> getAllUsers();

    void insertUser(User user);
}
