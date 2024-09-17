package com.together.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.together.pojo.Comment;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author w
 */
@Repository
public interface CommentMapper extends BaseMapper<Comment> {
}
