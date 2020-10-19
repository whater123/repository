package com.together.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.together.dao.ActivityMapper;
import com.together.dao.CommentMapper;
import com.together.dao.RelationMapper;
import com.together.dao.UserMapper;
import com.together.pojo.Activity;
import com.together.pojo.Comment;
import com.together.pojo.Relation;
import com.together.pojo.Theme;
import com.together.service.Imp.ActivityServiceImp;
import com.together.util.DistanceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author w
 */
@Service
public class ActivityService implements ActivityServiceImp {
    @Autowired
    ActivityMapper activityMapper;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    RelationMapper relationMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    ThemeService themeService;
    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Override
    public List<Activity> getActivityByTheme(int themeId) {
        List<Activity> activities;
        if (themeId == -1){
            activities = activityMapper.selectList(null);
        }
        else {
            QueryWrapper<Activity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("theme_id",themeId);
            activities = activityMapper.selectList(queryWrapper);
        }
        if (activities.isEmpty()){
            return null;
        }else {
            return activities;
        }
    }

    @Override
    public List<Activity> getRecommendActivities(List<Activity> activities) {
        activities.sort((activity1, activity2) -> {
                double distance = activity1.getDistance() - activity2.getDistance();
                int likeCount = activity1.getLikeCount() - activity2.getLikeCount();
                int commentCount = activity1.getCommentCount() - activity2.getCommentCount();
                int joinNumber = activity1.getJoinNumber() - activity2.getJoinNumber();
                int timeState = activity1.getTimeState() - activity2.getTimeState();
                return (int) distance-(likeCount*10 + commentCount*50 + joinNumber*200 + timeState*2000); }
                );
        if (activities.size()>5){
            return activities.subList(0, 5);
        }
        else{
            return activities;
        }
    }

    @Override
    public List<Activity> prefectActivities(List<Activity> activities,double longitude, double latitude) {
        //完善事件属性
        for (Activity activity: activities
        ) {
            QueryWrapper<Relation> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("activity_id",activity.getActivityId());
            QueryWrapper<Comment> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("activity_id",activity.getActivityId());

            activity.setUserLauncherName(userMapper.selectById(activity.getUserLauncherId()).getName());
            activity.setThemeName(themeService.getTheme(activity.getThemeId()).getName());
            activity.setJoinNumber(relationMapper.selectCount(queryWrapper));
            activity.setCommentCount(commentMapper.selectCount(queryWrapper1));
            if (redisTemplate.opsForValue().get(String.valueOf(activity.getActivityId())) != null){
                activity.setLikeCount((Integer) redisTemplate.opsForValue().get(String.valueOf(activity.getActivityId())));
            }
            else {
                activity.setLikeCount(0);
            }
            activity.setDistance(DistanceUtil.getDistance(activity.getLongitude(),activity.getLatitude(),longitude,latitude));
        }
        return activities;
    }

    @Override
    public void likeActivity(int activityId) {
        redisTemplate.opsForValue().increment(String.valueOf(activityId));
    }

    @Override
    public List<Activity> returnHandle(List<Activity> activities) {
        for (Activity activity: activities
             ) {
            activity.setContactWay(null);
            activity.setContext(null);
        }
        return activities;
    }

    @Override
    public boolean launchActivity(Activity activity) {
        //可以加上数据效验
        int insert = activityMapper.insert(activity);
        return insert == 1;
    }

    @Override
    public Map<String, Object> getActivityAndCommentsById(int activityId,double longitude, double latitude) {
        HashMap<String,Object> activityAndComments = new HashMap<>(2);
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("activity_id",activityId);
        List<Activity> activityList = new ArrayList<>();

        Activity activity = activityMapper.selectById(activityId);
        if (activity != null){
            activityList.add(activity);
            List<Activity> activities = prefectActivities(activityList, longitude, latitude);
            activity = activities.get(0);
        }

        List<Comment> comments = commentMapper.selectList(queryWrapper);
        activityAndComments.put("activity",activity);
        activityAndComments.put("comments",comments);
        return activityAndComments;
    }

    @Override
    public Relation getRelation(int activityId, int userId) {
        QueryWrapper<Relation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("activity_id",activityId).eq("user_id",userId);
        return relationMapper.selectOne(queryWrapper);
    }


}
