package com.springboot.mybatis.service.Imp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springboot.mybatis.pojo.StateCode;
import com.springboot.mybatis.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Random;

public interface FreshmanSignUpImp {

    boolean isQqExisted(User user);

    boolean isIdExisted(User user);

    void insertSignUp(User user);

    void insertSignUp_mogul(User user);

    void updateLoginId(String num, String id);

    boolean isSignedUp(String number);

    User getLoginUser(HttpServletRequest request) throws JsonProcessingException;

    User getContextUser(String context) throws JsonProcessingException;

    User mergeUser(User loginUser,User contextUser) throws JsonProcessingException;

    User getUser_normal(String context,HttpServletRequest request) throws JsonProcessingException;

    User getUser_mogul(String context,HttpServletRequest request) throws JsonProcessingException;

    void setRandomId(User user);

    StateCode isUserRule(User user);

    User deleteUserBlank(User user);
}
