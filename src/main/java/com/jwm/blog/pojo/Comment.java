package com.jwm.blog.pojo;

import com.jwm.blog.dto.DetailedBlog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private Long id;
    private String nickname;
    private String email;
    private String content;
    //头像
    private String avatar;
    private Date createTime;

    // 评论间的父子关系
    private Long blogId;
    private Long parentCommentId;
    private String parentNickname;

    //回复评论
    private List<Comment> replyComments = new ArrayList<>();
    private Comment parentComment;

    // 一个评论只能对应一个博客
    private DetailedBlog blog;

}
