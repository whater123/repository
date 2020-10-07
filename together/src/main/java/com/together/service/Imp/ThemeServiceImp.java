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
}
