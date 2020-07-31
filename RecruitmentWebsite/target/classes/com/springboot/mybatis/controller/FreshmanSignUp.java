package com.springboot.mybatis.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.springboot.mybatis.pojo.StateCode;
import com.springboot.mybatis.pojo.User;
import com.springboot.mybatis.service.FreshmanSignUpService;
import com.springboot.mybatis.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author w
 */
@RestController
public class FreshmanSignUp {
    @Autowired
    JsonUtil jsonUtil;
    @Autowired
    FreshmanSignUpService freshmanSignUpService;


    @RequestMapping(value = "/user/ifSignUp", produces = "application/json;charset=UTF-8")
    public String ifSignUp(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        try {
            User user = freshmanSignUpService.getLoginUser(request);
            if (!freshmanSignUpService.isSignedUp(user.getNumber())) {
                return jsonUtil.getJson(new StateCode("0", "未报名"));
            }
        } catch (Exception e) {
            return jsonUtil.getJson(new StateCode("-1", "出现错误"));
        }
        return jsonUtil.getJson(new StateCode("1", "已报名"));
    }

    /**
     * getSession，获取用户名和密码来修改user_register表的id
     *
     * @param context c
     * @return 1
     * @throws JsonProcessingException 1
     */
    @RequestMapping(value = "/user/freshmanSignUp_normal", produces = "application/json;charset=UTF-8")
    public String normal(@RequestBody String context, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        User user;
        try {
            user = freshmanSignUpService.getUser_normal(context, request);
            StateCode stateCode = freshmanSignUpService.isUserRule(freshmanSignUpService.deleteUserBlank(user));
            if("-1".equals(stateCode.getState())){
                return jsonUtil.getJson(stateCode);
            }
            if (freshmanSignUpService.isSignedUp(user.getNumber())) {
                return jsonUtil.getJson(new StateCode("-1", "报名失败，您已报名"));
            }
            freshmanSignUpService.setRandomId(user);
            if (freshmanSignUpService.isQqExisted(user)) {
                return jsonUtil.getJson(new StateCode("-1", "报名失败，QQ号码重复"));
            }
        } catch (Exception e) {
            return jsonUtil.getJson(new StateCode("-1", "报名失败，出现错误"));
        }
        freshmanSignUpService.insertSignUp(user);
        freshmanSignUpService.updateLoginId(user.getNumber(), user.getId());
        return jsonUtil.getJson(new StateCode("3", "新生报名成功"));
    }

    @RequestMapping(value = "/user/freshmanSignUp_mogul", produces = "application/json;charset=UTF-8")
    public String mogul(@RequestBody String context, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        User user;
        try {
            user = freshmanSignUpService.getUser_mogul(context, request);
            StateCode stateCode = freshmanSignUpService.isUserRule(freshmanSignUpService.deleteUserBlank(user));
            if("-1".equals(stateCode.getState())){
                return jsonUtil.getJson(stateCode);
            }
            if (freshmanSignUpService.isSignedUp(user.getNumber())) {
                return jsonUtil.getJson(new StateCode("-1", "报名失败，您已报名"));
            }
            freshmanSignUpService.setRandomId(user);
            if (freshmanSignUpService.isQqExisted(user)) {
                return jsonUtil.getJson(new StateCode("-1", "报名失败，QQ号码重复"));
            }
        } catch (Exception e) {
            return jsonUtil.getJson(new StateCode("-1", "报名失败，出现错误"));
        }
        freshmanSignUpService.insertSignUp_mogul(user);
        freshmanSignUpService.updateLoginId(user.getNumber(), user.getId());
        return jsonUtil.getJson(new StateCode("3", "新生报名成功"));
    }

}
