package com.together.service.Imp;

import java.util.List;

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

    List<String> getUserFavour(String id);
}
