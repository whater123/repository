package com.together.controller;

import com.together.pojo.Activity;
import com.together.pojo.Comment;
import com.together.pojo.returnPojo.Data;
import com.together.pojo.returnPojo.ReturnData;
import com.together.service.ActivityService;
import com.together.service.CommentService;
import com.together.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author w
 */
@RestController
@RequestMapping("/activity")
public class ActivityPageController {
    @Autowired
    ActivityService activityService;
    @Autowired
    CommentService commentService;
    @Autowired
    ThemeService themeService;

    @RequestMapping(path = "/getFiveActivities",produces = "application/json;charset=UTF-8")
    public ReturnData getActivities(@RequestBody Activity activity)throws Exception{
        try {
            List<Activity> activityByTheme = activityService.getActivityByTheme(activity.getThemeId());
            if (activityByTheme == null) {
                return new ReturnData("1", true, "不存在该主题");
            }
            List<Activity> activities = activityService.prefectActivities(activityByTheme, activity.getLongitude(), activity.getLatitude());
            List<Activity> recommendActivities = activityService.getRecommendActivities(activities);
            return new ReturnData("0", false, new Data(activityService.returnHandle(recommendActivities), 1));
        }catch (Exception e){
            e.printStackTrace();
            return new ReturnData("500", true, "服务器错误");
        }
    }

    @RequestMapping(path = "/getAllActivities",produces = "application/json;charset=UTF-8")
    public ReturnData getAllActivities(@RequestBody Activity activity)throws Exception{
        try {
            List<Activity> activityByTheme = activityService.getActivityByTheme(activity.getThemeId());
            if (activityByTheme == null) {
                return new ReturnData("1", true, "不存在该主题");
            }
            List<Activity> activities = activityService.prefectActivities(activityByTheme, activity.getLongitude(), activity.getLatitude());
            return new ReturnData("0", false, new Data(activityService.returnHandle(activities), 1));
        }catch (Exception e){
            e.printStackTrace();
            return new ReturnData("500", true, "服务器错误");
        }
    }

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

    @RequestMapping(path = "/getActivityById",produces = "application/json;charset=UTF-8")
    public ReturnData getActivityById(@RequestBody Activity activity){
        try {
            Map<String, Object> activityAndCommentsById = activityService.getActivityAndCommentsById(activity.getActivityId(), activity.getLongitude(), activity.getLatitude());
            Activity activity1 = (Activity) activityAndCommentsById.get("activity");
            List<Comment> comments = (List<Comment>) activityAndCommentsById.get("comments");
            if (activity1 == null) {
                return new ReturnData("1", true, "事件id有误");
            } else {
                return new ReturnData("0", true, new Data(commentService.perfectComments(comments), activity1));
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ReturnData("500", true, "服务器错误");
        }
    }

}
