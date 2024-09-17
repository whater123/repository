package com.together.controller;

import com.together.pojo.Activity;
import com.together.pojo.returnPojo.ReturnData;
import com.together.service.ActivityService;
import com.together.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    ThemeService themeService;
    @Autowired
    ActivityService activityService;

    @RequestMapping(path = "/launchActivity",produces = "application/json;charset=UTF-8")
    public ReturnData launchActivity(@RequestBody Activity activity){
        try {
            if (themeService.getTheme(activity.getThemeId()) == null){
                return new ReturnData("1", true, "不存在该主题");
            }
            if (activityService.launchActivity(activity)) {
                return new ReturnData("0", false);
            } else {
                return new ReturnData("1", true, "发布失败，请重试");
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ReturnData("500", true, "服务器错误");
        }
    }
}
