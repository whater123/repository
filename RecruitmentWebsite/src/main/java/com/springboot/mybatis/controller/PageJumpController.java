package com.springboot.mybatis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author w
 */
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
    public String toApplication(){
        return "application";
    }

    @RequestMapping("/notice")
    public String toNotice(){
        return "notice";
    }
}
