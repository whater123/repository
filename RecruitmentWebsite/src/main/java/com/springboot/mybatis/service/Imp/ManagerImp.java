package com.springboot.mybatis.service.Imp;

import com.springboot.mybatis.dao.ManagerMapper;
import com.springboot.mybatis.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @author w
 */
public interface ManagerImp {
    /**
     * 返回所有新生部分信息（用于页面显示
     * @return list
     */
    List<User> getAllUserMsg();

    /**
     * 根据id返回新生对象
     * @param id id
     * @return 目标user
     */
    User getUserMsg(String id);

    /**
     * 选择查询新生列表
     * @return list
     */
    List<User> getSomeUserMsg(User user);
}
