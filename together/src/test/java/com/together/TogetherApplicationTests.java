package com.together;

import com.together.service.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;

@SpringBootTest
class TogetherApplicationTests {
    @Autowired
    MailService mailService;
    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Test
    void contextLoads() {
        mailService.sendSimplemail("308932645@qq.com","test","芜湖");
    }

    @Test
    void t(){
        String regex6 = "[1-9]\\d{7,10}@qq\\.com";
        String string = "308932645@q.com";
        System.out.println(string.matches(regex6));
    }

    @Test
    void tokenTest(){
        redisTemplate.opsForHash().put("tokens","token1",1);
        redisTemplate.opsForHash().put("tokens","token2",2);

        Set<Object> keys = redisTemplate.opsForHash().keys("tokens");
        System.out.println(keys);
        System.out.println(keys.contains("token1"));
    }
}
