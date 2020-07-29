package com.springboot.mybatis.service;

import com.springboot.mybatis.dao.ManagerMapper;
import com.springboot.mybatis.pojo.User;
import com.springboot.mybatis.service.Imp.ManagerImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author w
 */
@Service
public class ManagerService implements ManagerImp {
    @Autowired
    ManagerMapper managerMapper;

    @Override
    public List<User> getAllUserMsg() {
        List<User> all = managerMapper.getAll();
        for (User i : all
             ) {
            //对前端显示部分得到数据部分屏蔽，因为是名单所以不应该拥有过多的信息
            i.setPassword(null);
        }
        return all;
    }

    @Override
    public User getUserMsg(String id) {
        List<User> all = managerMapper.getAll();
        for (User i : all
        ) {
            if (i.getId().equals(id)){
                return i;
            }
        }
        return new User();
    }

    @Override
    public List<User> getSomeUserMsg(User user) {
        Map<String,String> stringStringMap = new HashMap<>(8);
        stringStringMap.put("name",user.getName());
        stringStringMap.put("number",user.getNumber());
        stringStringMap.put("college",user.getCollege());
        stringStringMap.put("classroom",user.getClassroom());
        stringStringMap.put("id",user.getId());
        stringStringMap.put("department",user.getDepartment());
        stringStringMap.put("major",user.getMajor());
        stringStringMap.put("isdalao",user.getIsdalao());
        stringStringMap.put("interviewState",user.getInterviewState());
        return managerMapper.getSomeUser(stringStringMap);
    }


    @Override
    public Integer updateInterviewId(String id, String targetId,String isDalao) {
        return managerMapper.updateInterviewId(id, targetId,isDalao);
    }

    @Override
    public Integer updateStageStatus(List ids, Integer status) {
        return managerMapper.updateStageStatus(ids, status);
    }
}
