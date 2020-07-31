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

    //判断类
    /**
    * 判断user中的qq号是否已经在数据库中存在
    * */
    boolean isQqExisted(User user);

    /**
     * 判断user中的id是否已经在数据库中存在
     * */
    boolean isIdExisted(User user);

    /**
     * 判断通过学号（number）是否报名
     * */
    boolean isSignedUp(String number);

    /**
     * 判断该User对象中的报名数据是否合法
     * */
    StateCode isUserRule(User user);


    //数据库操作
    /**
     * 在user_signup中插入user的部分数据
     * */
    void insertSignUp(User user);

    /**
     * 在user_signup插入user的部分数据
     * 在user_dalao插入user的部分数据
     * */
    void insertSignUp_mogul(User user);

    /**
     * 在user_register依据num修改id
     * */
    void updateLoginId(String num, String id);

    /**
     * 通过学号获得
     * user_register和user_signup数据的User对象
     * */
    User getUserByNumber(String number);


    //User对象操作
    /**
     * 获得报名后全数据的User对象（普通报名）
     * */
    User getUser_normal(String context,HttpServletRequest request) throws JsonProcessingException;

    /**
     * 获得报名后全数据的User对象（大佬报名）
     * */
    User getUser_mogul(String context,HttpServletRequest request) throws JsonProcessingException;

    /**
     * 通过session获得当前登录User对象
     * */
    User getLoginUser(HttpServletRequest request) throws JsonProcessingException;

    /**
     * 通过json转化成User对象
     * */
    User getContextUser(String context) throws JsonProcessingException;

    /**
     * 在User对象里设置随机id
     * */
    void setRandomId(User user);

    /**
     * 删除User对象中的报名数据中的空格（除开特长和个人说明）
     * */
    User deleteUserBlank(User user);

    /**
     * 合并 当前登录的User对象和报名生成json所转化的User对象
     * */
    User mergeUser(User loginUser,User contextUser) throws JsonProcessingException;

}
