package com.together.pojo.returnPojo;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.together.pojo.Theme;
import com.together.pojo.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

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
    private List<Theme> themeList;
    private Theme theme;
    private User user;

    public Data(String token,User user) {
        this.token = token;
        this.user = user;
    }

    public Data(List<Theme> themeList) {
        this.themeList = themeList;
    }
}
