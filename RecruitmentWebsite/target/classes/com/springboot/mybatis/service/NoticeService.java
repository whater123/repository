package com.springboot.mybatis.service;

import com.springboot.mybatis.dao.NoticeMapper;
import com.springboot.mybatis.pojo.Notice;
import com.springboot.mybatis.service.Imp.NoticeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService implements NoticeImpl {

    @Autowired
    NoticeMapper noticeMapper;

    @Override
    public Notice getNoticeById(Integer id) {
        return noticeMapper.getNoticeById(id);
    }

    @Override
    public List<Notice> getAllNotices() {
        return noticeMapper.getAll();
    }

    @Override
    public Integer addNotice(Notice notice) {
        return noticeMapper.addNotice(notice);
    }

    @Override
    public Integer updateNoticeById(Notice notice) {
        return noticeMapper.updateNotice(notice);
    }

    @Override
    public Integer deleteNoticeById(Integer id) {
        return noticeMapper.deleteNotice(id);
    }
}
