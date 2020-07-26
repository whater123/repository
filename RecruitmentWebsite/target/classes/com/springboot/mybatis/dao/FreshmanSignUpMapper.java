package com.springboot.mybatis.dao;

import com.springboot.mybatis.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


import java.util.List;

@Mapper
@Repository
public interface FreshmanSignUpMapper {

    void insertSignUp(User user);

    void insertSignUp_mogul(User user);

    List<User> getAllSignUp();

    void updateLoginId(String num, String id);

    List<User> getAllRegister();
}
