package com.springboot.mybatis.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springboot.mybatis.pojo.Notice;
import com.springboot.mybatis.pojo.StateCode;
import com.springboot.mybatis.service.NoticeService;
import com.springboot.mybatis.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/manager")
public class NoticeController {

    @Autowired
    JsonUtil jsonUtil;
    @Autowired
    NoticeService noticeService;

    @PostMapping(value = "/getNoticeById", produces = "application/json;charset=UTF-8")
    public String getNoticeByid(@RequestBody String context) throws IOException {
        Map<String, String> map = (Map<String, String>) jsonUtil.getObject(context, Map.class);
        Integer id = Integer.valueOf(map.get("id"));
        Notice notice = noticeService.getNoticeById(id);
        System.out.println(notice);
        return jsonUtil.getJson(notice);
    }

    @PostMapping(value = "/getAllNotice", produces = "application/json;charset=UTF-8")
    public String getAllNotice() throws JsonProcessingException {
        List<Notice> notices = noticeService.getAllNotices();
        return jsonUtil.getJson(notices);
    }

    @PostMapping(value = "/updateNotice", produces = "application/json;charset=UTF-8")
    public String updateNotice(@RequestBody String context) throws IOException {
        Notice notice = (Notice) jsonUtil.getObject(context, Notice.class);
        Date date = new Date();
        notice.setDate(date);
        try {
            Integer row = noticeService.updateNoticeById(notice);
            if (row > 0) {
                return jsonUtil.getJson(new StateCode("0", "修改成功"));
            } else {
                return jsonUtil.getJson(new StateCode("-1", "该用户不存在，修改失败"));
            }
        } catch (Exception e) {
            return jsonUtil.getJson(new StateCode("-1", "后端出现异常"));
        }
    }

    @PostMapping(value = "/deleteNotice", produces = "application/json;charset=UTF-8")
    public String deleteNotice(@RequestBody String context) throws IOException {
        Map<String, String> map = (Map<String, String>) jsonUtil.getObject(context, Map.class);
        Integer id = Integer.valueOf(map.get("id"));
        try {
            Integer row = noticeService.deleteNoticeById(id);
            if (row>0){
                return jsonUtil.getJson(new StateCode("0","删除成功"));
            }else {
                return jsonUtil.getJson(new StateCode("-1","该用户不存在，删除失败"));
            }
        }catch (Exception e){
            return jsonUtil.getJson(new StateCode("-1","后端出现异常"));
        }
    }

    @PostMapping(value = "/addNotice",produces = "application/json;charset=UTF-8")
    public String addNotice(@RequestBody String context) throws IOException {
        Notice notice = (Notice) jsonUtil.getObject(context, Notice.class);
        Date date = new Date();
        notice.setDate(date);
        try {
            Integer row = noticeService.addNotice(notice);
            if(row>0){
                return jsonUtil.getJson(new StateCode("0", "添加成功"));
            }else {
                return jsonUtil.getJson(new StateCode("-1","添加失败"));
            }
        }catch (Exception e){
            return jsonUtil.getJson(new StateCode("-1","后端异常"));
        }
    }
}
