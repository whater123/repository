package com.together.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author w
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User implements Serializable {
    @TableId(type = IdType.AUTO)
    private int userId;

    @TableField(value = "user_name")
    private String name;

    @TableField(value = "user_password")
    private String password;

    @TableField(value = "user_email")
    private String email;

    @TableField(value = "user_phone")
    private String phone;

    @TableField(value = "user_level")
    private int level;

    @TableField(value = "user_head_name")
    private String headName;

    @TableField(value = "user_register_time",fill = FieldFill.INSERT)
    private Date registerTime;
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

    @TableField(exist = false)
    private String longitude;

    @TableField(exist = false)
    private String latitude;

    public User(int userId, String name, String password, String email, String phone, int level, String headName, Date registerTime) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.level = level;
        this.headName = headName;
        this.registerTime = registerTime;
    }
}
