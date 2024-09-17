package com.together.service.Imp;

import com.together.pojo.User;

import java.util.List;

/**
 * @author w
 */
public interface UserServiceImp {
    /**
     * 返回电话是否被注册
     * @param phone 电话
     * @return boolean
     */
    boolean phoneIsRegistered(String phone);

    /**
     * 效验手机号是否合法
     * @param phone 手机号
     * @return boolean
     */
    boolean isPhone(String phone);

    /**
     * 用户注册
     * @param user 用户
     */
    void userRegister(User user);

    /**
     * 验证密码是否合法
     * @param password 密码
     * @return boolean
     */
    boolean isPassword(String password);

    /**
     * 用户登录
     * @param phone 电话
     * @param password 密码
     * @return 验证通过返回user对象，未通过返回null
     */
    User loginAndGetUser(String phone,String password);

    /**
     * 根据id设置token到redis,并返回token
     * @param id 用户id
     * @return token
     */
    String setAndGetTokenById(String id);

    /**
     * 延时7天token的有效期
     * @param token 用户token
     */
    void delayedTokenByToken(String token);

    /**
     * 获取用户id
     * @param phone 用户电话
     * @return id
     */
    int getUserIdByPhone(String phone);

    /**
     * 根据token获取用户id
     * @param token token
     * @return id
     */
    String getUserIdByToken(String token);

    /**
     * 根据id获取user对象
     * @param id id
     * @return user
     */
    User getUserById(int id);

    /**
     * 对返回数据进行处理
     * @param user user
     * @return user
     */
    User returnHandle(User user);

    /**
     * 更改user信息
     * @param user user
     */
    void updateUser(User user);

    /**
     * 根据id搜索token，没有则返回null
     * @param id id
     * @return token/null
     */
    String getTokenById(int id);
}
