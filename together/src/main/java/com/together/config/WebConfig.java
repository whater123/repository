package com.together.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

/**
 * @author w
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private InterceptorConfig interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加拦截器，配置拦截地址
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")
                //设置放行url
                .excludePathPatterns("/login", "/getAllThemes","/register","/isRegistered","/getMailCode","/changePassword","/error");
    }
}
