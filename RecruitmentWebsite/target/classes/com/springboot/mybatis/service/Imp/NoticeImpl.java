package com.springboot.mybatis.service.Imp;

import com.springboot.mybatis.pojo.Notice;

import java.util.List;

public interface NoticeImpl {

    /**
     * 根据Id获取通知
     * @param id
     * @return 返回通知的POJO
     */
    Notice getNoticeById(Integer id);

    /**
     * 获取所有的通知
     * @return 返回封装所有通知POJO的list
     */
    List<Notice> getAllNotices();

    /**
     * 添加通知
     * @param notice
     * @return 返回已成功添加通知的个数
     */
    Integer addNotice(Notice notice);

    /**
     * 更新通知
     * @param notice
     * @return 返回成功更新通知的个数
     */
    Integer updateNoticeById(Notice notice);

    /**
     * 根据Id删除通知
     * @param id
     * @return 返回成功删除通知的个数
     */
    Integer deleteNoticeById(Integer id);
}
