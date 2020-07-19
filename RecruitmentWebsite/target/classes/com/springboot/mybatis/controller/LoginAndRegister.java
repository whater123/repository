package com.springboot.mybatis.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springboot.mybatis.pojo.StateCode;
import com.springboot.mybatis.pojo.User;
import com.springboot.mybatis.service.LoginAndRegisterService;
import com.springboot.mybatis.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author w
 */
@RestController
public class LoginAndRegister {

    @Autowired
    LoginAndRegisterService loAndReService;
    @Autowired
    JsonUtil jsonUtil;

    @RequestMapping(value = "/register" , produces = "application/json;charset=UTF-8")
    public String register(@RequestBody String context) throws JsonProcessingException {
        User user = (User) jsonUtil.getObject(context, User.class);
        StateCode registered = loAndReService.isRegistered(user);

        if ("0".equals(registered.getState())){
            loAndReService.userRegister(user);
            return jsonUtil.getJson(new StateCode("0","注册成功"));
        }
        else {
            return jsonUtil.getJson(registered);
        }

    }

    @RequestMapping(value = "/login" , produces = "application/json;charset=UTF-8")
    public String login(@RequestBody String context) throws JsonProcessingException {
        User user = (User) jsonUtil.getObject(context, User.class);
        if (loAndReService.canLogin(user)){
            return jsonUtil.getJson(user.returnLoginCode());
        }
        else {
            return jsonUtil.getJson(new StateCode("-1","用户名或密码错误，登录失败"));
        }
    }
}
