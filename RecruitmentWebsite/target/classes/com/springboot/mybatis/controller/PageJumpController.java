package com.springboot.mybatis.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springboot.mybatis.pojo.User;
import com.springboot.mybatis.util.JsonUtil;
import com.springboot.mybatis.util.SystemParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author w
 */
@CrossOrigin
@Controller
public class PageJumpController {

    @RequestMapping("/toTest")
    public String toText(){
        return "test";
    }

    @RequestMapping("/form")
    public String toForm(){
        return "form";
    }

    @RequestMapping("/application")
    public String toApplication(HttpServletRequest request) throws JsonProcessingException {
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("user");
        JsonUtil jsonUtil = new JsonUtil();
        if (user != null&&SystemParam.isManager((User) jsonUtil.getObject(user,User.class))){
            return "application";
        }
        else{
            return "index";
        }
    }

    @RequestMapping("/notice")
    public String toNotice(){
        return "notice";
    }
}
