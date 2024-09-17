package com.together.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author w
 */
@Controller
public class PageController {
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }
}
