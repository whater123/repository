package com.together;

import com.together.service.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TogetherApplicationTests {
    @Autowired
    MailService mailService;

    @Test
    void contextLoads() {
        mailService.sendSimplemail("2243693385@qq.com","test","芜湖");
    }

}
