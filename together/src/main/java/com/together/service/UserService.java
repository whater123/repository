package com.together.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.together.dao.UserMapper;
import com.together.pojo.User;
import com.together.service.Imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author w
 */
@Service
public class UserService implements UserServiceImp {

    @Autowired
    UserMapper userMapper;
    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Override
    public boolean phoneIsRegistered(String phone) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("phone",phone);
        List<User> users = userMapper.selectByMap(map);
        return !users.isEmpty();
    }

    @Override
    public boolean isPhone(String phone) {
        if (phone == null) {
            return false;
        }
        String regex = "^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$";
        return phone.matches(regex);
    }

    @Override
    public List<String> getUserFavour(String id) {
        return (List<String>) redisTemplate.opsForHash().get("users", id);
    }

    @Override
    public void userRegister(User user) {
        userMapper.insert(user);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("phone",user.getPhone());
        String id = String.valueOf(userMapper.selectOne(queryWrapper).getId());
        //将偏爱主题存入redis
        redisTemplate.opsForHash().put("users",id,user.getFavour());

    }

    @Override
    public boolean isPassword(String password) {
        if (password == null) {
            return false;
        }
        String pwRegex = "^[\\w]{8,16}$";
        return password.matches(pwRegex);
    }

    @Override
    public User login(String phone, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("phone",phone).in("password",password);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public String getToken(String id) {
        UUID uuid = UUID.randomUUID();
        //设置7天有效期
        redisTemplate.opsForValue().set(String.valueOf(uuid),id,7, TimeUnit.DAYS);
        return String.valueOf(uuid);
    }

    @Override
    public void delayedToken(String token) {
        int id = Integer.parseInt(getUserIdByToken(token));
        redisTemplate.opsForValue().set(String.valueOf(token),id,7, TimeUnit.DAYS);
    }

    @Override
    public int getUserId(String phone) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("phone",phone);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null){
            return 0;
        }
        else {
            return user.getId();
        }
    }

    @Override
    public String getUserIdByToken(String token) {
        return String.valueOf(redisTemplate.opsForValue().get(token));
    }

    @Override
    public User getUserById(int id) {
        return userMapper.selectById(id);
    }

    @Override
    public User returnHandle(User user) {
        user.setPassword(null);
        user.setFavour(getUserFavour(String.valueOf(user.getId())));
        user.setRegisterTime(null);
        return  user;
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateById(user);
    }
}
