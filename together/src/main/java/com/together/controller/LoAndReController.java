package com.together.controller;

import com.together.pojo.returnPojo.Data;
import com.together.pojo.returnPojo.ReturnData;
import com.together.pojo.Theme;
import com.together.pojo.User;
import com.together.service.MailService;
import com.together.service.ThemeService;
import com.together.service.UserService;
import com.together.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author w
 */
@RestController
public class LoAndReController {

    @Autowired
    RedisTemplate<String,Object> redisTemplate;
    @Autowired
    ThemeService themeService;
    @Autowired
    VerificationService verificationService;
    @Autowired
    MailService mailService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/getAllThemes",produces= "application/json;charset=UTF-8")
    public ReturnData getAllThemes() {
        try{
            List<Theme> allThemes = themeService.getAllThemes();
            return new ReturnData("0",false,null,new Data(allThemes));
        }catch (Exception e){
            return new ReturnData("500",true,String.valueOf(e));
        }
    }

    @RequestMapping(value = "/isRegistered",produces= "application/json;charset=UTF-8")
    public ReturnData isRegistered(@RequestBody User user) throws JSONException {
        try{
            String phone = user.getPhone();
        if (!userService.isPhone(phone)){
            return new ReturnData("1",true,"手机号非法");
        }
        if (userService.phoneIsRegistered(phone)){
            //已被注册
            return new ReturnData("1",true,"手机号已被注册");
        }else {
            //未被注册
            return new ReturnData("0",false);
        }}catch (Exception e){
            return new ReturnData("500",true,String.valueOf(e));
        }
    }

    @RequestMapping(value = "/getMailCode",produces = "application/json;charset=UTF-8")
    public ReturnData getMailCode(@RequestBody User user) throws JSONException {
        try{
            String mail = user.getEmail();
            if (!mailService.isEmail(mail)) {
                return new ReturnData("1",true,"邮箱输入非法");
            }
            String code = verificationService.getCode();
            //key为邮箱，有效时间为10分钟
            redisTemplate.opsForValue().set(mail,code,60*10, TimeUnit.SECONDS);

            mailService.sendSimplemail(
                    mail,
                    "约吧邮箱验证",
                    "[约吧] 验证码："+code+"（10分钟内有效）。您正在登陆约吧账号，请勿将验证码告诉其他人哦");

            return new ReturnData("0",false);
        }catch (Exception e){
            return new ReturnData("500",true,String.valueOf(e));
        }
    }
}
