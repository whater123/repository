package com.springboot.mybatis.dao;

import com.springboot.mybatis.pojo.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface NoticeMapper {

    Notice getNoticeById(Integer id);

    Notice getLatestNotice();

    List<Notice> getAll();

    Integer addNotice(Notice notice);

    Integer updateNotice(Notice notice);

    Integer deleteNotice(Integer id);
}
