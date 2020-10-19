package com.together.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author w
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Relation implements Serializable {
    private int activityId;

    private int userId;

    @TableField(value = "relation_punch_card")
    private int hasPunchCard;

    @TableField(value = "relation_launcher")
    private int isLauncher;
}
