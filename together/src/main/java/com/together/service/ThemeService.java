package com.together.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.together.dao.ThemeMapper;
import com.together.pojo.Theme;
import com.together.service.Imp.ThemeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author w
 */
@Service
public class ThemeService implements ThemeServiceImp {

    @Autowired
    ThemeMapper themeMapper;

    @Override
    public List<Theme> getAllThemes() {
        return themeMapper.selectList(null);
    }

    @Override
    public Theme getTheme(int id) {
        return themeMapper.selectById(id);
    }

    @Override
    public Theme getTheme(String theme) {
        QueryWrapper<Theme> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("theme_name",theme);
        return themeMapper.selectOne(queryWrapper);
    }
}
