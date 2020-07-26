package com.springboot.mybatis.service;

import com.springboot.mybatis.dao.FreshmanSignUpMapper;
import com.springboot.mybatis.pojo.User;
import com.springboot.mybatis.service.Imp.FreshmanSignUpImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
@Service
public class FreshmanSignUpService implements FreshmanSignUpImp {
    @Autowired
    FreshmanSignUpMapper freshmanSignUpMapper;

    @Override
    public boolean isQqExisted(User user) {
        List<User> list = freshmanSignUpMapper.getAllSignUp();
        for(User i : list){
            if(user.getQq().equals(i.getQq())){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isIdExisted(User user) {
        List<User> list = freshmanSignUpMapper.getAllSignUp();
        for(User i : list){
            if(user.getId().equals(i.getId())){
                return true;
            }
        }
        return false;
    }

    @Override
    public void insertSignUp(User user) {
        freshmanSignUpMapper.insertSignUp(user);
    }

    @Override
    public void insertSignUp_mogul(User user) {
        freshmanSignUpMapper.insertSignUp(user);
        freshmanSignUpMapper.insertSignUp_mogul(user);
    }

    @Override
    public void updateLoginId(String num, String id) {
        freshmanSignUpMapper.updateLoginId(num,id);
    }

    @Override
    public boolean isSignedUp(String number) {
        List<User> list = freshmanSignUpMapper.getAllRegister();
        for(User i : list){
            if(number.equals(i.getNumber())){
                if("0".equals(i.getId())){
                    return false;
                }
                else{
                    return true;
                }
            }
        }
        return false;
    }
}
