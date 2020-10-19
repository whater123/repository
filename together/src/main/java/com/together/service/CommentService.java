package com.together.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.together.dao.CommentMapper;
import com.together.dao.UserMapper;
import com.together.pojo.Comment;
import com.together.pojo.User;
import com.together.service.Imp.CommentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author w
 */
@Service
public class CommentService implements CommentServiceImp {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public List<Comment> perfectComments(List<Comment> commentList) {
        for (Comment comment: commentList
             ) {
            comment.setUserName(userMapper.selectById(comment.getUserId()).getName());
        }
        return commentList;
    }
}
