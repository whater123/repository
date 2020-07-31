package com.springboot.mybatis.dao;

import com.springboot.mybatis.pojo.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {

    Notice getNoticeById(Integer id);

    List<Notice> getAll();

    Integer addNotice(Notice notice);

    Integer updateNotice(Notice notice);

    Integer deleteNotice(Integer id);
}
