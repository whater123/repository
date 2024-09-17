package com.together.service.Imp;

import com.together.pojo.Activity;
import com.together.pojo.Relation;

import java.util.List;
import java.util.Map;

/**
 * @author w
 */
public interface ActivityServiceImp {

    /**
     * 根据主题获取动态列表
     * @param themeId 主题限制，-1为不做限制
     * @return 该主题的动态列表
     */
    List<Activity> getActivityByTheme(int themeId);

    /**
     * 根据总事件列表和经纬度推荐5个事件
     * @param activities 符合主题筛选的总事件
     * @return 推荐的5个事件
     */
    List<Activity> getRecommendActivities(List<Activity> activities);

    /**
     * 根据经纬度补充完整事件信息
     * @param longitude 经度
     * @param latitude 纬度
     * @param activities 未完善的事件列表
     * @return 完整属性的事件列表
     */
    List<Activity> prefectActivities(List<Activity> activities,double longitude, double latitude);

    /**
     * 根据事件id点赞（redis
     * @param activityId 事件id
     */
    void likeActivity(int activityId);

    /**
     * 处理返回数据
     * @param activities ac
     * @return list
     */
    List<Activity> returnHandle(List<Activity> activities);

    /**
     * 发布事件
     * @param activity 事件信息
     * @return 是否发布成功
     */
    boolean launchActivity(Activity activity);

    /**
     * 以id获取事件全部信息和所有评论
     * @param activityId id
     * @param longitude 经度
     * @param latitude 纬度
     * @return "activity"：事件信息 "comments"：所有评论列表
     */
    Map<String,Object> getActivityAndCommentsById(int activityId,double longitude, double latitude);

    /**
     * 获取用户与事件的关系
     * @param activityId 事件id
     * @param userId 用户id
     * @return 返回是否为发起人，是否已打卡
     */
    Relation getRelation(int activityId,int userId);
}
