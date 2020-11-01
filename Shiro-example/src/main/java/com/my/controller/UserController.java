package com.my.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController{

    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    public String getMessage() {
        return "您拥有用户权限，可以获得该接口的信息！";
    }
}