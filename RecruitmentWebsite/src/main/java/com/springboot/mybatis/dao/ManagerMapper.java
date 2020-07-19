package com.springboot.mybatis.dao;

import com.springboot.mybatis.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author w
 */
@Mapper
@Repository
public interface ManagerMapper {
    List<User> getAll();

    List<User> getSomeUser(Map<String,String> stringStringMap);
}
