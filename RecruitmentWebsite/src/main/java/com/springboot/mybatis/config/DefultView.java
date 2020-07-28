package com.springboot.mybatis.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author w
 */
@Configuration
public class DefultView implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry)

    {
        registry.addViewController("/").setViewName("");

    }
}
