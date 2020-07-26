package com.springboot.mybatis.pojo;

import com.springboot.mybatis.util.SystemParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author w
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String name;
    private String qq;
    private String number;
    private String password;
    private String college;
    private String classroom;
    private String id;
    private String context;
    private String major;
    private String department;
    private String isdalao;
    private String stageState;
    private String specialty;

    /**
     *登陆成功后的判断权限
     */
    public User(String number,String password){
        this.number = number;
        this.password = password;
    }

    public StateCode returnLoginCode(){
        if (SystemParam.isManager(this)){
            return new StateCode("1","管理员登录成功");
        }
        else {
            return new StateCode("2","新生登录成功");
        }
    }

    public String getInterviewState(){
        if ("1".equals(stageState)||"2".equals(stageState)){
            return "通过";
        }
        else {
            return "未通过";
        }
    }

    public String getBigWorkState(){
        if ("0".equals(stageState)||"1".equals(stageState)){
            return "通过";
        }
        else {
            return "未通过";
        }
    }
}

