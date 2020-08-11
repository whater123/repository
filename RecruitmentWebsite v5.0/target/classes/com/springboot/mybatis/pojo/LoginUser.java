package com.springboot.mybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author w
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class LoginUser extends User{
    private String remember;
    public void initLoginUser(User user){
        super.setName(user.getName());
        super.setPassword(user.getPassword());
        super.setQq(user.getQq());
        super.setClassroom(user.getClassroom());
        super.setCollege(user.getCollege());
        super.setContext(user.getContext());
        super.setDepartment(user.getDepartment());
        super.setId(user.getId());
        super.setIsdalao(user.getIsdalao());
        super.setMajor(user.getMajor());
        super.setNumber(user.getNumber());
        super.setSpecialty(user.getSpecialty());
        super.setStageState(user.getStageState());
    }
}
