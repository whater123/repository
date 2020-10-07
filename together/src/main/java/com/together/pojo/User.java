package com.together.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

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
    //在UserService里get后set
    @TableField(exist = false)//表示该属性不为数据库表字段，但又是必须使用的。
    private List<String> favour = null;

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
