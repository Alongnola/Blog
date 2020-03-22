package com.jwm.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jwm.blog.dto.FirstPageBlog;
import com.jwm.blog.pojo.Tag;
import com.jwm.blog.service.BlogService;
import com.jwm.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TagShowController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/tags/{id}")
    public String tag(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                      @PathVariable Long id, Model model) {
        List<Tag> tags = tagService.getTagsByBlog();
        //默认推荐首个标签的多有微博
        if (id == -1) {
            id = tags.get(0).getId();
        }
        model.addAttribute("tags", tags);
        List<FirstPageBlog> blogs = blogService.getByTagId(id);
        PageHelper.startPage(pageNum, 100);
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("activeTagId", id);
        return "tags";
    }
}
