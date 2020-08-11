package com.springboot.mybatis.util;

import com.springboot.mybatis.pojo.User;

/**
 * @author w
 */
public class SystemParam {
    static public final String MANAGER_NUMBER = "202000000000";
    static public final String MANAGER_PASSWORD = "2019fanlu";

    static public boolean isManager(User user){
        return MANAGER_NUMBER.equals(user.getNumber()) && MANAGER_PASSWORD.equals(user.getPassword());
    }
}
