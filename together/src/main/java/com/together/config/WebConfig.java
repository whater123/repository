package com.together.config;

import com.together.util.StringToDateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

/**
 * @author w
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private InterceptorConfig interceptor;
    @Autowired
    private RequestMappingHandlerAdapter handlerAdapter;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加拦截器，配置拦截地址
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")
                //设置放行url
                .excludePathPatterns("/login", "/getAllThemes","/register","/isRegistered","/getMailCode","/changePassword","/error");
    }

    /**
     * 增加字符串转日期的功能
     */
    @PostConstruct
    public void initEditableAvlidation() {
        ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer)handlerAdapter.getWebBindingInitializer();
        assert initializer != null;
        if(initializer.getConversionService()!=null) {
            GenericConversionService genericConversionService = (GenericConversionService)initializer.getConversionService();
            genericConversionService.addConverter(new StringToDateConverter());
        }
    }
}
