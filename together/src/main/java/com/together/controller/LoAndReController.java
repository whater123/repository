package com.together.controller;

import com.together.pojo.returnPojo.Data;
import com.together.pojo.returnPojo.ReturnData;
import com.together.pojo.Theme;
import com.together.pojo.User;
import com.together.service.MailService;
import com.together.service.ThemeService;
import com.together.service.UserService;
import com.together.service.VerificationService;
import com.together.util.MD5util;
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
            e.printStackTrace();
            return new ReturnData("500",true,"服务器错误");
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
            e.printStackTrace();
            return new ReturnData("500",true,"服务器错误");
        }
    }

    @RequestMapping(value = "/getMailCode",produces = "application/json;charset=UTF-8")
    public ReturnData getMailCode(@RequestBody User user) throws JSONException {
        try{
            String mail = user.getEmail();
            if (!mailService.isEmail(mail)) {
                return new ReturnData("1",true,"邮箱输入非法或已被注册");
            }

            String code = verificationService.getCode();
            mailService.sendSimplemail(
                    mail,
                    "约吧邮箱验证",
                    "[约吧] 验证码："+code+"（10分钟内有效）。您正在登陆约吧账号，请勿将验证码告诉其他人哦");
            //key为邮箱，有效时间为10分钟
            redisTemplate.opsForValue().set(mail,code,60*10, TimeUnit.SECONDS);

            return new ReturnData("0",false);
        }catch (Exception e){
            String message = e.getMessage();
            if (message.contains("Invalid Addresses;")) {
                return new ReturnData("1",true,"邮箱输入非法");
            }
            else {
                e.printStackTrace();
                return new ReturnData("500",true,"服务器错误");
            }
        }
    }

    @RequestMapping(value = "/register",produces = "application/json;charset=UTF-8")
    public ReturnData register(@RequestBody User user){
        try{
            //默认信誉分为10
            user.setScore(10);
            String code = (String) redisTemplate.opsForValue().get(user.getEmail());
            if (code == null){
                return new ReturnData("1",true,"未发送邮箱验证码");
            }
            if (!userService.isPassword(user.getPassword())){
                return new ReturnData("1",true,"密码非法");
            }
            if (code.equalsIgnoreCase(user.getCode())){
                user.setPassword(MD5util.code(user.getPassword()));
                userService.userRegister(user);
                redisTemplate.delete(user.getEmail());
                return new ReturnData("0",false);
            }
            else {
                return new ReturnData("1",true,"邮箱验证错误");
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ReturnData("500",true,"服务器错误");
        }
    }

    @RequestMapping(value = "/login",produces = "application/json;charset=UTF-8")
    public ReturnData login(@RequestBody User user){
        try{
            User login = userService.login(user.getPhone(), MD5util.code(user.getPassword()));
        if (login == null){
            return new ReturnData("1",true,"手机号或密码错误");
        }
        else {
            login.setId(userService.getUserId(user.getPhone()));
            login = userService.returnHandle(login);
            //设置7天的token
            String token = userService.getToken(String.valueOf(login.getId()));
            return new ReturnData("0",false,new Data(token,login));
        }}catch (Exception e){
            e.printStackTrace();
            return new ReturnData("500",true,"服务器错误");
        }
    }

    @RequestMapping(value = "/loginToken",produces = "application/json;charset=UTF-8")
    public ReturnData loginToken(@RequestBody User user){
        try {
            String userIdByToken = userService.getUserIdByToken(user.getToken());
            if ("null".equals(userIdByToken)) {
                return new ReturnData("1", true, "未登录");
            }
            User userById = userService.getUserById(Integer.parseInt(userIdByToken));
            //token延期7天
            userService.delayedToken(user.getToken());
            userById = userService.returnHandle(userById);
            return new ReturnData("0", false, new Data(userById));
        }
        catch (Exception e){
            e.printStackTrace();
            return new ReturnData("500", true, "服务器错误");
        }
    }

    @RequestMapping(value = "/changePassword",produces = "application/json;charset=UTF-8")
    public ReturnData changePassword(@RequestBody User user){
        try {
            if (!"ture".equals(user.getCanRefreshPassword().trim())) {
                return new ReturnData("1", true, "非法访问");
            }
            if (!userService.isPassword(user.getPassword())) {
                return new ReturnData("1", true, "密码非法");
            }
            int userId = userService.getUserId(user.getPhone());
            if (userId == 0) {
                return new ReturnData("1", true, "手机号未注册");
            }
            User userById = userService.getUserById(userId);
            userById.setPassword(MD5util.code(user.getPassword()));
            userService.updateUser(userById);
            return new ReturnData("0", false);
        }catch (Exception e){
            e.printStackTrace();
            return new ReturnData("500", true, "服务器错误");
        }
    }
}
