package com.together;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author w
 */
@MapperScan("com.together.dao")
@SpringBootApplication
public class TogetherApplication {

    public static void main(String[] args) {
        SpringApplication.run(TogetherApplication.class, args);
    }

}
