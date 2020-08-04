package com.springboot.mybatis.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springboot.mybatis.pojo.Notice;
import com.springboot.mybatis.pojo.StateCode;
import com.springboot.mybatis.service.NoticeService;
import com.springboot.mybatis.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class NoticeController {

    @Autowired
    JsonUtil jsonUtil;
    @Autowired
    NoticeService noticeService;

    @PostMapping(value = "/manager/getNoticeById", produces = "application/json;charset=UTF-8")
    public String getNoticeByid(@RequestBody String context) throws IOException {
        Map<String, String> map = (Map<String, String>) jsonUtil.getObject(context, Map.class);
        Integer id = Integer.valueOf(String.valueOf(map.get("id")));
        Notice notice = noticeService.getNoticeById(id);
        return jsonUtil.getJson(notice);
    }

    @PostMapping(value = "/user/getNewNotice",produces = "application/json;charset=UTF-8")
    public String getNewNotice() throws JsonProcessingException {
        try {
            Notice latestNotice = noticeService.getLatestNotice();
            if(latestNotice!=null){
                return jsonUtil.getJson(latestNotice);
            }else {
                return jsonUtil.getJson(new StateCode("-1","目前还没有通知!"));
            }
        } catch (Exception e) {
            return jsonUtil.getJson(new StateCode("-1","后端出现异常"));
        }
    }

    @PostMapping(value = "/manager/getAllNotice", produces = "application/json;charset=UTF-8")
    public String getAllNotice() throws JsonProcessingException {
        List<Notice> notices = noticeService.getAllNotices();
        return jsonUtil.getJson(notices);
    }

    @PostMapping(value = "/manager/updateNotice", produces = "application/json;charset=UTF-8")
    public String updateNotice(@RequestBody String context) throws IOException {
        Notice notice = (Notice) jsonUtil.getObject(context, Notice.class);
        Date date = new Date();
        notice.setDate(date);
        try {
            Integer row = noticeService.updateNoticeById(notice);
            if (row > 0) {
                return jsonUtil.getJson(new StateCode("5", "修改成功"));
            } else {
                return jsonUtil.getJson(new StateCode("-1", "该通知不存在，修改失败"));
            }
        } catch (Exception e) {
            return jsonUtil.getJson(new StateCode("-1", "后端出现异常"));
        }
    }

    @PostMapping(value = "/manager/deleteNotice", produces = "application/json;charset=UTF-8")
    public String deleteNotice(@RequestBody String context) throws IOException {
        Map<String, String> map = (Map<String, String>) jsonUtil.getObject(context, Map.class);
        Integer id = Integer.valueOf(String.valueOf(map.get("id")));
        try {
            Integer row = noticeService.deleteNoticeById(id);
            if (row>0){
                return jsonUtil.getJson(new StateCode("5","删除成功"));
            }else {
                return jsonUtil.getJson(new StateCode("-1","该通知不存在，删除失败"));
            }
        }catch (Exception e){
            return jsonUtil.getJson(new StateCode("-1","后端出现异常"));
        }
    }

    @PostMapping(value = "/manager/addNotice",produces = "application/json;charset=UTF-8")
    public String addNotice(@RequestBody String context) throws IOException {
        Notice notice = (Notice) jsonUtil.getObject(context, Notice.class);
        Date date = new Date();
        notice.setDate(date);
        try {
            Integer row = noticeService.addNotice(notice);
            if(row>0){
                return jsonUtil.getJson(new StateCode("5", "添加成功"));
            }else {
                return jsonUtil.getJson(new StateCode("-1","添加失败"));
            }
        }catch (Exception e){
            return jsonUtil.getJson(new StateCode("-1","后端异常"));
        }
    }
}
