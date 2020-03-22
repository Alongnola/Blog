package com.jwm.blog.service;

import com.jwm.blog.dto.*;
import com.jwm.blog.pojo.Blog;

import java.util.List;
import java.util.Map;

public interface BlogService {
    ShowBlog getBlogById(Long id);

    //
    List<BlogInfo> getBlogInfoList();

    int saveBlog(Blog blog);

    int updateBlog(ShowBlog showBlog);

    int deleteBlog(Long id);

    List<BlogInfo> getBlogBySearch(SearchBlog searchBlog);

    //修改recommend,因为recommend从前台接收只能接收字符串，但数据库中的Integer类型，所以转一下
    void transformRecommend(SearchBlog searchBlog);

    List<FirstPageBlog> getAllBlogs();

    List<RecommendBlog> getAllRecommendedBlogs();

    List<FirstPageBlog> getSearchBlog(String query);

    DetailedBlog getDetailedBlog(Long id);

    //根据TypeId获取博客列表，在分类页进行的操作
    List<FirstPageBlog> getByTypeId(Long typeId);

    List<FirstPageBlog> getByTagId(Long tagId);

    int addView(Long id);

    Map<String, List<Blog>> archiveBlog();

    Long countBlog();
}
