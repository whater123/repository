package com.together.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * @author w
 */
@Configuration
public class InterceptorConfig  implements HandlerInterceptor {
    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果是登陆页面则放行
        System.out.println("拦截器生效: " + request.getRequestURI());
        if (request.getRequestURI().contains("ogin")) {
            return true;
        }
        //验证token，前端也是
        String token = request.getHeader("token");
        Set<String> keys = redisTemplate.keys("*-*");
        if (keys == null){
            return false;
        }
        return keys.contains(token);
    }
}
