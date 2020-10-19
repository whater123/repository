package com.together.pojo.returnPojo;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.together.pojo.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author w
 */
@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Data {
    private String token;
    private Theme theme;
    private User user;
    private Activity activity;
    private Relation relation;
    private List<Theme> themeList;
    private List<Activity> activityList;
    private List<Comment> commentList;

    public Data(Relation relation) {
        this.relation = relation;
    }

    public Data(List<Comment> commentList, Activity activity) {
        this.commentList = commentList;
        this.activity = activity;
    }

    public Data(List<Activity> activityList, int key) {
        this.activityList = activityList;
    }

    public Data(User user) {
        this.user = user;
    }

    public Data(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public Data(List<Theme> themeList) {
        this.themeList = themeList;
    }

    public List<String> getThemeList(){
        if(this.themeList == null){
            return null;
        }
        List<String> themeList = new ArrayList<>();
        for (Theme theme :this.themeList
                ) {
            themeList.add(theme.getName());
        }
        return themeList;
    }
}
