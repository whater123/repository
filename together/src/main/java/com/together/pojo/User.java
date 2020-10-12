package com.together.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @author w
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    @TableId(type = IdType.AUTO)//id自增
    private int id;
    private String name;
    private String password;
    private String email;
    private String phone;
    private int sex;
    private int age;
    private String introduce;
    private int score;
    private String headName;

    @TableField(fill = FieldFill.INSERT)
    private Date registerTime;
    /**
     * 偏爱主题
     */
    @TableField(exist = false)
    private List<String> favour;
    /**
     * 关注id列表
     */
    @TableField(exist = false)
    private List<Integer> followCount;
    /**
     * 粉丝id列表
     */
    @TableField(exist = false)
    private List<Integer> fanCount;
    /**
     * 邮箱验证码
     */
    @TableField(exist = false)
    private String code;

    /**
     * 能否进行忘记密码的操作
     */
    @TableField(exist = false)
    private String canRefreshPassword;

    /**
     * 免密登录token
     */
    @TableField(exist = false)
    private String token;

    public User(int id, String name, String password, String email, String phone, int sex, int age, String introduce, int score, String headName) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.sex = sex;
        this.age = age;
        this.introduce = introduce;
        this.score = score;
        this.headName = headName;
    }
}
