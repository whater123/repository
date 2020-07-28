package com.springboot.mybatis.service;

import com.springboot.mybatis.dao.UerRegisterMapper;
import com.springboot.mybatis.pojo.StateCode;
import com.springboot.mybatis.pojo.User;
import com.springboot.mybatis.service.Imp.LoginAndRegisterImp;
import com.springboot.mybatis.util.SystemParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author w
 */
@Service
public class LoginAndRegisterService implements LoginAndRegisterImp {
    @Autowired
    UerRegisterMapper uerRegisterMapper;

    @Override
    public StateCode isRegistered(User user) {
        List<User> allUsers = uerRegisterMapper.getAllUsers();
        for (User i : allUsers
             ) {
            if (i.getNumber().equals(user.getNumber())){
                return new StateCode("-1","学号已被注册");
            }
            //不能与管理员重名
            else if (SystemParam.MANAGER_NUMBER.equals(user.getNumber())){
                return new StateCode("-1","学号非法");
            }
        }
        return new StateCode("0","");
    }

    @Override
    public void userRegister(User user){
        uerRegisterMapper.insertUser(user);
    }

    @Override
    public boolean canLogin(User user) {
        if (SystemParam.isManager(user)){
            return true;
        }
        List<User> allUsers = uerRegisterMapper.getAllUsers();
        for (User i : allUsers
             ) {
            if (i.getNumber().equals(user.getNumber())
                    &&i.getPassword().equals(user.getPassword())){
                return true;
            }
        }
        return false;
    }

    @Override
    public User getUserByNumber(String number) {
        List<User> allUsers = uerRegisterMapper.getAllAll();
        for (User i : allUsers
                ) {
            if (i.getNumber().equals(number)){
                return i;
            }
        }
        return new User("","");
    }

}
