package com.together;

import com.together.dao.CommentMapper;
import com.together.pojo.Activity;
import com.together.service.ActivityService;
import com.together.service.MailService;
import com.together.service.ThemeService;
import com.together.service.UserService;
import com.together.util.DistanceUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@SpringBootTest
class TogetherApplicationTests {
    @Autowired
    MailService mailService;
    @Autowired
    UserService userService;
    @Autowired
    ActivityService activityService;
    @Autowired
    ThemeService themeService;
    @Autowired
    CommentMapper commentMapper;
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
        System.out.println(DistanceUtil.getDistance(113.311182,28.316266,113.48971,27.984494));

    }

    @Test
    void t3() throws ParseException {
        System.out.println(LocalDateTime.now());
    }
}
