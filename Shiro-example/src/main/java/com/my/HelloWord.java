package com.my;

        import org.mybatis.spring.annotation.MapperScan;
        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author w
 */
@MapperScan("com.my.dao")
@SpringBootApplication
public class HelloWord {
    public static void main(String[] args) {
        //spring应用启动
        SpringApplication.run(HelloWord.class,args);
    }
}
