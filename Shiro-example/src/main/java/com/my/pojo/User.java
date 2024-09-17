package com.my.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author w
 */
@Data
@AllArgsConstructor
public class User {
    @TableId(type = IdType.AUTO)
    private int id;
    @TableField(value = "user_name")
    private String userName;
    private String password;
    private String role;
}
