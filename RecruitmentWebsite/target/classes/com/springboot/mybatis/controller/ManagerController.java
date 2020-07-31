package com.springboot.mybatis.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springboot.mybatis.pojo.StateCode;
import com.springboot.mybatis.pojo.User;
import com.springboot.mybatis.service.ManagerService;
import com.springboot.mybatis.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Random;

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


    @PostMapping(value = "/editStatus",produces = "application/json;charset=UTF-8")
    public String updateStageStatus(@RequestBody String context) throws JsonProcessingException {
        Map map = (Map) jsonUtil.getObject(context, Map.class);
        List ids = (List) map.get("ids");
        Integer status = Integer.valueOf((String) map.get("status"));
        try{
            Integer row = managerService.updateStageStatus(ids, status);
            if(row < ids.size()){
                return jsonUtil.getJson(new StateCode("-1","部分用户id不存在，未能修改"));
            }else {
                return jsonUtil.getJson(new StateCode("0","修改成功"));
            }
        }catch (Exception e){
            e.printStackTrace();
            return jsonUtil.getJson(new StateCode("-1","后端异常，修改失败"));
        }
    }

    /**
     * 修改三个表的id
     * @param context 1
     * @return 1
     * @throws JsonProcessingException 1
     */
    @PostMapping(value = "/editId",produces = "application/json;charset=UTF-8")
    public String updateInterviewId(@RequestBody String context) throws JsonProcessingException {
        Map map = (Map) jsonUtil.getObject(context, Map.class);
        String id = (String) map.get("id");
        String targetId = (String) map.get("targetId");


        User userMsg = managerService.getUserMsg(id);
        String isExist = managerService.getUserMsg(targetId).getNumber();
        if(isExist !=null){
            Random random = new Random();
            String newId = String.valueOf(random.nextInt(1000));
            while(managerService.getUserMsg(newId).getNumber()!=null){
                newId = String.valueOf(random.nextInt(1000));
            }
            return jsonUtil.getJson(new StateCode("-1","该id已存在，你可以使用这个id来进行修改："+newId));
        }
        String isdalao = userMsg.getIsdalao();
        try{
            Integer row = managerService.updateInterviewId(id, targetId,isdalao);
            if(row == 0){
                return jsonUtil.getJson(new StateCode("-1","没有该用户，修改失败"));
            }else {
                return jsonUtil.getJson(new StateCode("0","修改成功"));
            }
        }catch (Exception e){
            e.printStackTrace();
            return jsonUtil.getJson(new StateCode("-1","后端异常,修改失败"));
        }
    }
}
