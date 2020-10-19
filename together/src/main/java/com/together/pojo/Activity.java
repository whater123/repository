package com.together.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author w
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Activity implements Serializable {
    @TableId(type = IdType.AUTO)
    private int activityId;

    private int themeId;

    private int userLauncherId;

    @TableField(exist = false)
    private String userLauncherName;

    @TableField(exist = false)
    private String themeName;

    @TableField(value = "activity_title")
    private String title;

    @TableField(value = "activity_context")
    private String context;

    @TableField(value = "activity_contact_way")
    private String contactWay;

    @TableField(value = "activity_start_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    private Date startTime;

    @TableField(value = "activity_stop_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    private Date stopTime;

    @TableField(value = "activity_longitude")
    private double longitude;

    @TableField(value = "activity_latitude")
    private double latitude;

    @TableField(exist = false)
    private int likeCount;

    @TableField(exist = false)
    private int commentCount;

    /**
     *  正在进行/未开始/已结束
     */
    @TableField(exist = false)
    private int timeState;

    @TableField(exist = false)
    private double distance;

    @TableField(exist = false)
    private int joinNumber;

    public Activity(int activityId, int themeId, String title, String context, String contactWay, Date startTime, Date stopTime, double longitude, double latitude) {
        this.activityId = activityId;
        this.themeId = themeId;
        this.title = title;
        this.context = context;
        this.contactWay = contactWay;
        this.startTime = startTime;
        this.stopTime = stopTime;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    /**
     * 1表示未开始，2表示正在进行，0表示已经结束
     */
    public int getTimeState(){
        Date nowDate = new Date();
        if (nowDate.before(startTime)){
            return 1;
        }
        else if (nowDate.after(stopTime)){
            return 0;
        }
        else {
            return 2;
        }
    }
}
