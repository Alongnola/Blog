package com.jwm.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 博客列表页进行多条件查询时所使用的类
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchBlog {
    private String title;
    private Long typeId;
    private String recommend;
    private Integer recommend2;
}
