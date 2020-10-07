package com.together.service;

import com.together.dao.ThemeMapper;
import com.together.pojo.Theme;
import com.together.service.Imp.ThemeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
