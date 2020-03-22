package com.jwm.blog.service.Impl;

import com.jwm.blog.dto.*;
import com.jwm.blog.exception.NotFoundException;
import com.jwm.blog.mapper.BlogMapper;
import com.jwm.blog.pojo.Blog;
import com.jwm.blog.pojo.Tag;
import com.jwm.blog.service.BlogService;
import com.jwm.blog.util.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper dao;

    @Override
    public ShowBlog getBlogById(Long id) {
        return dao.getBlogById(id);
    }

    @Transactional
    @Override
    public List<BlogInfo> getBlogInfoList() {
        List<BlogInfo> allBlogQuery = dao.getBlogInfoList();
        return allBlogQuery;
    }

    @Transactional
    @Override
    public int saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        List<Tag> tags = blog.getTags();
        BlogAndTag blogAndTag = null;
        for (Tag tag : tags) {
            blogAndTag = new BlogAndTag(tag.getId(), blog.getId());
            dao.saveBlogAndTag(blogAndTag);
        }
        return dao.saveBlog(blog);
    }

    @Override
    public int updateBlog(ShowBlog showBlog) {
        showBlog.setUpdateTime(new Date());
        return dao.updateBlog(showBlog);
    }

    @Override
    public int deleteBlog(Long id) {
        dao.deleteBlogAndTag(id);
        dao.deleteBlog(id);
        return 1;
    }

    // 查找微博
    @Override
    public List<BlogInfo> getBlogBySearch(SearchBlog searchBlog) {
        return dao.searchByTitleOrTypeOrRecommend(searchBlog);
    }

    @Override
    public void transformRecommend(SearchBlog searchBlog) {
        if (!"".equals(searchBlog.getRecommend()) && null != searchBlog.getRecommend()) {
            searchBlog.setRecommend2(1);
        }
    }

    @Override
    public List<FirstPageBlog> getAllBlogs() {
        return dao.getAllBlogs();
    }


    @Override
    public List<RecommendBlog> getAllRecommendedBlogs() {
        List<RecommendBlog> allRecommendBlog = dao.getAllRecommendBlogs();
        List<RecommendBlog> allRecommendedBlog = new ArrayList<>();
        for (RecommendBlog recommendBlog : allRecommendBlog) {
            if (recommendBlog.isRecommend() == true) {
                allRecommendedBlog.add(recommendBlog);
            }
        }
        return allRecommendedBlog;
    }

    @Override
    public List<FirstPageBlog> getSearchBlog(String query) {
        return dao.getSearchBlog(query);
    }

    @Override
    public DetailedBlog getDetailedBlog(Long id) {
        DetailedBlog detailedBlog = dao.getDetailedBlog(id);
        if (detailedBlog == null) {
            throw new NotFoundException("该博客不存在");
        }
        String content = detailedBlog.getContent();
        detailedBlog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        return detailedBlog;
    }

    @Override
    public List<FirstPageBlog> getByTypeId(Long typeId) {
        return dao.getByTypeId(typeId);
    }

    @Override
    public List<FirstPageBlog> getByTagId(Long tagId) {
        return dao.getByTagId(tagId);
    }

    @Override
    public int addView(Long id) {
        return dao.addView(id);
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        Calendar calendar = Calendar.getInstance();
        List<Blog> blogs = dao.archiveBlog();
        Map<Integer, List<Blog>> map = new HashMap<>();
        for (Blog blog : blogs) {
            calendar.setTime(blog.getUpdateTime());
            int year = calendar.get(Calendar.YEAR);
            if (!map.containsKey(year)) {
                map.put(year, new ArrayList<Blog>());
            }
            map.get(year).add(blog);
        }


        Map<String, List<Blog>> ret = new HashMap<>();
        for (Map.Entry<Integer, List<Blog>> entry : map.entrySet()) {
            ret.put(String.valueOf(entry.getKey()), entry.getValue());
        }
        return ret;
    }

    @Override
    public Long countBlog() {
        return new Long(dao.getAllBlogs().size());
    }

}
