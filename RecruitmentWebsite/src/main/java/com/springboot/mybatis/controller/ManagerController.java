package com.springboot.mybatis.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springboot.mybatis.pojo.User;
import com.springboot.mybatis.service.ManagerService;
import com.springboot.mybatis.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author w
 */
@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    JsonUtil jsonUtil;
    @Autowired
    ManagerService managerService;

    @RequestMapping(value = "/getAllStudents" , produces = "application/json;charset=UTF-8")
    public String getAllMsg() throws JsonProcessingException {
        return jsonUtil.getJson(managerService.getAllUserMsg());
    }

    @RequestMapping(value = "/getStudentMsg/{id}" )
    public String getMsg(@PathVariable String id) throws JsonProcessingException {
        return jsonUtil.getJson(managerService.getUserMsg(id));
    }

    @RequestMapping(value = "/queryStudents" , produces = "application/json;charset=UTF-8")
    public String getSomeMsg(@RequestBody String context) throws JsonProcessingException {
        User user = (User) jsonUtil.getObject(context, User.class);
        return jsonUtil.getJson( managerService.getSomeUserMsg(user));
    }

}
