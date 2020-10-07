package com.together.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author w
 */
@Configuration
public class InterceptorConfig  implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果是登陆页面则放行
        System.out.println("拦截器生效: " + request.getRequestURI());
        if (request.getRequestURI().contains("login")) {
            return true;
        }
        //验证token，前端也是
        return true;
    }

}
