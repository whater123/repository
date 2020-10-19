package com.together.service.Imp;

import com.together.pojo.Theme;

import java.util.List;

/**
 * @author w
 */
public interface ThemeServiceImp {
    /**
     *返回所有主题
     * @return 所有的主题
     */
    List<Theme> getAllThemes();

    /**
     * 根据id获取theme对象
     * @param id id
     * @return theme对象
     */
    Theme getTheme(int id);

    /**
     * 根据主题名获取theme对象
     * @param theme 主题名
     * @return theme对象
     */
    Theme getTheme(String theme);
}
