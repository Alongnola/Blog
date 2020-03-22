package com.jwm.blog.dto;

import com.jwm.blog.pojo.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 博客列表页显示数据所使用的类
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogInfo {
    private Long id;
    private String title;
    private Date updateTime;
    private Integer recommend;
    private Long typeId;

    private Type type;
}
