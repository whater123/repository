package com.springboot.mybatis.service.Imp;

import com.springboot.mybatis.pojo.StateCode;
import com.springboot.mybatis.pojo.User;

/**
 * @author w
 */
public interface LoginAndRegisterImp {
    /**
     * 是否已被注册
     * @param user 注册者
     * @return boolean
     */
    StateCode isRegistered(User user);

    /**
     * 新生注册
     * @param user 注册者
     */
    void userRegister(User user);

    /**
     * 判断学号密码是否正确
     * @param user 登陆者
     * @return boolean
     */
    boolean canLogin(User user);

    /**
     * 根据session信息的学号获取user
     * @param number number
     * @return user
     */
    User getUserByNumber(String number);
}
