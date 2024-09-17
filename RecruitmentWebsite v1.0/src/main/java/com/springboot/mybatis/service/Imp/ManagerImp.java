package com.springboot.mybatis.service.Imp;

import com.alibaba.druid.pool.ha.selector.StickyDataSourceHolder;
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

    /**
     * 修改新生面试Id
     * @param id 新生原Id
     * @param targetId 修改后的新生Id
     */
    Integer updateInterviewId(String id, String targetId, String isDalao);

    /**
     * 修改新生面试状态 异常：Cause: java.sql.SQLException: sql injection violation, multi-statement not allow : update user_signup set id = ? where id = ?;
     *         update user_register set id = ? where id = ?
     * @param ids 新生Id
     * @param status 修改后的新生面试/大作业状态
     */
    Integer updateStageStatus(List ids, Integer status);
}
