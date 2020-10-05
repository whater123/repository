package com.together.controller;

import com.together.pojo.StateCode;
import com.together.service.MailService;
import com.together.service.VerificationService;
import com.together.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author w
 */
@RestController
public class LoAndReController {

    @Autowired
    RedisTemplate<String,Object> redisTemplate;
    @Autowired
    VerificationService verificationService;
    @Autowired
    MailService mailService;
    @Autowired
    JsonUtil jsonUtil;

    @RequestMapping("/getMailCode")
    public String getMailCode(@RequestParam String userMail) throws JSONException {
        if (!mailService.isEmail(userMail)) {
            jsonUtil.putStateCode(new StateCode("0","邮箱非法，请重新输入"));
            return jsonUtil.toString();
        }
        String code = verificationService.getCode();
        //key为邮箱，有效时间为10分钟
        redisTemplate.opsForValue().set(userMail,code,60*10, TimeUnit.SECONDS);

        mailService.sendSimplemail(
                userMail,
                "约吧邮箱验证",
                "[约吧] 验证码："+code+"（10分钟内有效）。您正在登陆约吧账号，请勿将验证码告诉其他人哦");

        jsonUtil.putStateCode(new StateCode("1","邮箱验证码发送成功"));
        return jsonUtil.toString();
    }

    @RequestMapping("/submitMail")
    public String submitMail(@RequestParam String userMail,@RequestParam String code) throws JSONException {
        Boolean aBoolean = redisTemplate.hasKey(userMail);
        if (aBoolean == null || !aBoolean){
            //没有发送验证码
            jsonUtil.putStateCode(new StateCode("0","请先发送验证码"));
            return jsonUtil.toString();
        }
        if (String.valueOf(redisTemplate.opsForValue().get(userMail)).equalsIgnoreCase(code)){
            //验证码验证成功
            redisTemplate.delete(userMail);

            jsonUtil.putStateCode(new StateCode("1","验证成功！"));
            return jsonUtil.toString();
        }
        else{
            //验证码错误
            redisTemplate.delete(userMail);

            jsonUtil.putStateCode(new StateCode("0","验证码错误！"));
            return jsonUtil.toString();
        }
    }
}
