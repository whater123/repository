package com.springboot.mybatis.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springboot.mybatis.pojo.StateCode;
import com.springboot.mybatis.pojo.User;
import com.springboot.mybatis.service.LoginAndRegisterService;
import com.springboot.mybatis.util.JsonUtil;
import com.springboot.mybatis.util.SystemParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
    public String login(@RequestBody String context, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        //默认记住密码
        HttpSession session = request.getSession();
        User user = (User) jsonUtil.getObject(context, User.class);
        if (!loAndReService.canLogin(user)) {
            return jsonUtil.getJson(new StateCode("-1","用户名或密码错误，登录失败"));
        } else {
            //登录时设置session
            session.setAttribute("user", jsonUtil.getJson(user));
            return jsonUtil.getJson(user.returnLoginCode());
        }
    }

    /**
     * 如果获取不到就要直接跳转到登录页面
     * @param request request
     * @return user
     * @throws JsonProcessingException json
     */
    @RequestMapping(value = "/getSession" )
    public String getNowUser(HttpServletRequest request) throws JsonProcessingException {
        User user = (User)jsonUtil.getObject(getNowUserJson(request), User.class);
        return jsonUtil.getJson(user);
    }

    @RequestMapping("/loginOut")
    public String loginOut(HttpServletRequest request) throws JsonProcessingException {
        HttpSession session = request.getSession();
        User user = (User)jsonUtil.getObject(getNowUserJson(request), User.class);

        if (user == null|| "".equals(user.getNumber())|| "".equals(user.getPassword())){
            return jsonUtil.getJson(new StateCode("-1","您未登录，注销失败"));
        }
        else {
            session.invalidate();
            return jsonUtil.getJson(new StateCode("4","注销成功"));
        }
    }

    @RequestMapping("/userIsManager")
    public String isManager(HttpServletRequest request) throws JsonProcessingException {
        User user = (User)jsonUtil.getObject(getNowUserJson(request), User.class);
        return String.valueOf(SystemParam.isManager(user));
    }

    private String getNowUserJson(HttpServletRequest request) throws JsonProcessingException {
        User user;
        HttpSession session = request.getSession();
        String string = (String) session.getAttribute("user");
        if (string != null) {
            user = (User) jsonUtil.getObject(string, User.class);
        } else {
            user = new User("", "");
        }
        return jsonUtil.getJson(user);
    }
}
