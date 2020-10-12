package com.together;

import com.together.service.MailService;
import com.together.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

@SpringBootTest
class TogetherApplicationTests {
    @Autowired
    MailService mailService;
    @Autowired
    UserService userService;
    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Test
    void contextLoads() {
        mailService.sendSimplemail("308932645@qq.com","test","芜湖");
    }

    @Test
    void t(){
        String pwRegex = "^[\\w]{8,16}$";
        String string = "123456abc___";
        System.out.println(string.matches(pwRegex));
    }

    @Test
    void tokenTest(){
        redisTemplate.opsForHash().put("tokens","token1",1);
        redisTemplate.opsForHash().put("tokens","token2",2);

        Set<Object> keys = redisTemplate.opsForHash().keys("tokens");
        System.out.println(keys);
        System.out.println(keys.contains("token1"));
    }

    //ture
    @Test
    void t2(){
        System.out.println(userService.getUserId("11"));
    }
}
