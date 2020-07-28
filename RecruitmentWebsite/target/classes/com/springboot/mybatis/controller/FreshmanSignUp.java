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

/**
 * @author w
 */
@RestController
public class FreshmanSignUp {
    @Autowired
    JsonUtil jsonUtil;
    @Autowired
    FreshmanSignUpService freshmanSignUpService;


    @RequestMapping(value = "/user/ifSignUp" , produces = "application/json;charset=UTF-8")
    public String ifSignUp (HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException{
        try{
            HttpSession session = request.getSession();
            User user = (User)jsonUtil.getObject((String)session.getAttribute("user"),User.class);
            boolean isSignedUp = freshmanSignUpService.isSignedUp(user.getNumber());
            if(!isSignedUp){
                return jsonUtil.getJson(new StateCode("-1","未报名"));
            }
        }
        catch (Exception e){
            System.out.println(e);
            return jsonUtil.getJson(new StateCode("-1","出现错误"));
        }
        return jsonUtil.getJson(new StateCode("3","已报名"));
    }

    /**getSession，获取用户名和密码来修改user_register表的id
     * @param context c
     * @return 1
     * @throws JsonProcessingException 1
     */
    @RequestMapping(value = "/user/freshmanSignUp_normal" , produces = "application/json;charset=UTF-8")
    public String normal (@RequestBody String context, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        User user;
        try{
            HttpSession session = request.getSession();
            String str = (String) session.getAttribute("user");
            user = (User) jsonUtil.getObject(context,User.class);
            User user1 = (User) jsonUtil.getObject(str,User.class);
            if(freshmanSignUpService.isSignedUp(user1.getNumber())){
                return jsonUtil.getJson(new StateCode("-1","报名失败，您已报名"));
            }
            user.setNumber(user1.getNumber());
            user.setPassword(user1.getPassword());
            user.setIsdalao("0");
            Random r = new Random();
            user.setId(String.valueOf(r.nextInt(1000)));
            while(freshmanSignUpService.isIdExisted(user) || "0".equals(user.getId())){
                user.setId(String.valueOf(r.nextInt(1000)));
            }
            if(freshmanSignUpService.isQqExisted(user)){
                return jsonUtil.getJson(new StateCode("-1","报名失败，QQ号码重复"));
            }
        }
        catch (Exception e){
            return jsonUtil.getJson(new StateCode("-1","报名失败，出现错误"));
        }
        freshmanSignUpService.insertSignUp(user);
        freshmanSignUpService.updateLoginId(user.getNumber(),user.getId());
        return jsonUtil.getJson(new StateCode("3","新生报名成功"));
    }
    @RequestMapping(value = "/user/freshmanSignUp_mogul" , produces = "application/json;charset=UTF-8")
    public String mogul (@RequestBody String context, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        User user;
        try{
            HttpSession session = request.getSession();
            String str = (String) session.getAttribute("user");
            user = (User) jsonUtil.getObject(context,User.class);
            User user1 = (User) jsonUtil.getObject(str,User.class);

            if(freshmanSignUpService.isSignedUp(user1.getNumber())){
                return jsonUtil.getJson(new StateCode("-1","报名失败，您已报名"));
            }
            user.setNumber(user1.getNumber());
            user.setPassword(user1.getPassword());
            user.setIsdalao("1");
            Random r = new Random();
            user.setId(String.valueOf(r.nextInt(1000)));
            while(freshmanSignUpService.isIdExisted(user) || "0".equals(user.getId())){
                user.setId(String.valueOf(r.nextInt(1000)));
            }
            if(freshmanSignUpService.isQqExisted(user)){
                return jsonUtil.getJson(new StateCode("-1","报名失败，QQ号码重复"));
            }
        }
        catch (Exception e){
            return jsonUtil.getJson(new StateCode("-1","报名失败，出现错误"));
        }
        freshmanSignUpService.insertSignUp_mogul(user);
        freshmanSignUpService.updateLoginId(user.getNumber(),user.getId());
        return jsonUtil.getJson(new StateCode("3", "新生大佬报名成功"));
    }


}
