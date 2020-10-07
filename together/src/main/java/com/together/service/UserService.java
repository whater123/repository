package com.together.service;

import com.together.dao.UserMapper;
import com.together.pojo.User;
import com.together.service.Imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author w
 */
@Service
public class UserService implements UserServiceImp {

    @Autowired
    UserMapper userMapper;

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
        return null;
    }
}
