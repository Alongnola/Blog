package com.jwm.blog.mapper;

import com.jwm.blog.dto.*;
import com.jwm.blog.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface BlogMapper {

    ShowBlog getBlogById(Long id);

    List<BlogInfo> getBlogInfoList();

    int saveBlog(Blog blog);

    int deleteBlog(Long id);

    int updateBlog(ShowBlog showBlog);

    int saveBlogAndTag(BlogAndTag blogAndTag);

    int deleteBlogAndTag(Long blogId);

    List<BlogInfo> searchByTitleOrTypeOrRecommend(SearchBlog searchBlog);

    List<FirstPageBlog> getAllBlogs();

    List<RecommendBlog> getAllRecommendBlogs();

    List<FirstPageBlog> getSearchBlog(String query);

    DetailedBlog getDetailedBlog(Long id);

    List<FirstPageBlog> getByTypeId(Long typeId);

    List<FirstPageBlog> getByTagId(Long tagId);

    int addView(Long id);

    @Select("SELECT * FROM blog.t_blog")
    List<Blog> archiveBlog();

}
