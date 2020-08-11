package com.springboot.mybatis;

import com.springboot.mybatis.dao.ManagerMapper;
import com.springboot.mybatis.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class MybatisApplicationTests {

//    @Autowired
//    ManagerMapper managerMapper;
//    @Autowired
//    DataSource dataSource;

    @Test
    void contextLoads() throws SQLException {
//        List<User> all = managerMapper.getAll();
//        System.out.println(all);

    }
}
