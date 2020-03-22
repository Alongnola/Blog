package com.jwm.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jwm.blog.dto.DetailedBlog;
import com.jwm.blog.dto.FirstPageBlog;
import com.jwm.blog.dto.RecommendBlog;
import com.jwm.blog.pojo.Comment;
import com.jwm.blog.pojo.Tag;
import com.jwm.blog.pojo.Type;
import com.jwm.blog.service.BlogService;
import com.jwm.blog.service.CommentService;
import com.jwm.blog.service.TagService;
import com.jwm.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class IndexController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/")
    public String index(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum, 6);
        List<FirstPageBlog> blogs = blogService.getAllBlogs();

        List<Type> allType = typeService.getTypesByBlog();
        List<Tag> allTag = tagService.getTagsByBlog();

        // 推荐的微博
        List<RecommendBlog> recommendBlogs = blogService.getAllRecommendedBlogs();
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("tags", allTag);
        model.addAttribute("types", allType);
        model.addAttribute("recommendedBlogs", recommendBlogs);
        return "index";
    }

    @GetMapping("/search")
    public String search(Model model,
                         @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                         @RequestParam String query) {
        PageHelper.startPage(pageNum, 6);
        List<FirstPageBlog> searchBlog = blogService.getSearchBlog(query);
        /*for (FirstPageBlog firstPageBlog : searchBlog) {
            System.out.println(firstPageBlog);
        }*/
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(searchBlog);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("query", query);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) {
        blogService.addView(id);
        DetailedBlog detailedBlog = blogService.getDetailedBlog(id);
        List<Comment> comments = commentService.listCommentByBlogId(id);
        model.addAttribute("comments", comments);
        model.addAttribute("blog", detailedBlog);
        return "blog";
    }


}
