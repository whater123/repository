package com.together.service.Imp;

import com.together.pojo.Comment;

import java.util.List;

/**
 * @author w
 */
public interface CommentServiceImp {
    /**
     * 补充用户名
     * @param commentList 原评论列表
     * @return 完善后的评论列表
     */
    List<Comment> perfectComments(List<Comment> commentList);
}
