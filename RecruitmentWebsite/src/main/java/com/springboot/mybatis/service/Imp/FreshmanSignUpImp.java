package com.springboot.mybatis.service.Imp;

import com.springboot.mybatis.pojo.StateCode;
import com.springboot.mybatis.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface FreshmanSignUpImp {

    boolean isQqExisted(User user);

    boolean isIdExisted(User user);

    void insertSignUp(User user);

    void insertSignUp_mogul(User user);

    void updateLoginId(String num, String id);

    boolean isSignedUp(String number);

}
