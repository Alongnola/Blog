package com.jwm.blog.mapper;

import com.jwm.blog.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {
    //根据创建时间倒序来排
    List<Comment> findByBlogIdParentIdNull(@Param("blogId") Long blogId, @Param("blogParentId") Long blogParentId);

    //查询父级对象
    Comment findByParentCommentId(Long parentCommentId);

    List<Comment> findChildsByParentCommentId(Long parentCommentId);

    //添加一个评论
    int saveComment(Comment comment);

}
