package com.springboot.mybatis.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.springboot.mybatis.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author w
 */
@Controller
public class JsonUtil {
    public static ObjectMapper mapper = new ObjectMapper();

    static {
        // 转换为格式化的json
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        // 如果json中有新增的字段并且是实体类类中不存在的，不报错
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 将一个封装类实例转化为json字符串
     * @param pojo 任意封装类实例
     * @return 实例对应的json字符串
     * @throws JsonProcessingException JsonProcessingException
     */
    public String getJson(Object pojo) throws JsonProcessingException {
        return mapper.writeValueAsString(pojo);
    }

    /**
     * 将一个json字符串转化为实体类实例
     * @param json json字符串
     * @param pojoClass 目标封装类.class
     * @return 目标封装类
     * @throws JsonProcessingException JsonProcessingException
     */
    public Object getObject(String json, Class<?> pojoClass) throws IOException {
        return mapper.readValue(json,pojoClass);
    }

}
