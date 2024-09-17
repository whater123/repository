package com.springboot.mybatis.dao;

import com.springboot.mybatis.pojo.InterviewData;
import com.springboot.mybatis.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * @author MSI-PC
 */
@Mapper
@Repository
public interface FreshmanSignUpMapper {

    void insertSignUp(User user);

    void insertSignUp_mogul(User user);

    List<User> getAllSignUp();

    void updateLoginId(String num, String id);

    List<User> getAllRegister();

    String getSpecialtyById(String id);

    InterviewData getInterviewDataById(String id);
}
