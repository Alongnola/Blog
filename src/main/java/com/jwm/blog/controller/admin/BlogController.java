package com.jwm.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jwm.blog.dto.BlogInfo;
import com.jwm.blog.dto.SearchBlog;
import com.jwm.blog.dto.ShowBlog;
import com.jwm.blog.pojo.Blog;
import com.jwm.blog.pojo.Tag;
import com.jwm.blog.pojo.Type;
import com.jwm.blog.pojo.User;
import com.jwm.blog.service.BlogService;
import com.jwm.blog.service.TagService;
import com.jwm.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    public void setTypeAndTag(Model model) {
        model.addAttribute("types", typeService.getTypesForPage());
        model.addAttribute("tags", tagService.getTagsForPage());
    }

    //分页显示博客列表信息
    @GetMapping("/blogs")
    public String list(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum, 5);
        List<BlogInfo> allBlog = blogService.getBlogInfoList();
        PageInfo<BlogInfo> pageInfo = new PageInfo<>(allBlog);
        model.addAttribute("pageInfo", pageInfo);
        setTypeAndTag(model);
        return "admin/blogs";
    }

    //去新增页面
    @GetMapping("/blogs/input")
    public String toAdd(Model model) {
        setTypeAndTag(model);
        return "admin/blogs-input";
    }

    //发布微博
    @PostMapping("/blogs")
    public String add(Blog blog, RedirectAttributes attributes, HttpSession session) {
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getTypeById(blog.getType().getId()));
        blog.setTypeId(blog.getType().getId());
        blog.setTags(tagService.getTagByString(blog.getTagIds()));
        blog.setUserId(blog.getUser().getId());
        blogService.saveBlog(blog);
        attributes.addFlashAttribute("message", "发布成功！");
        return "redirect:/admin/blogs";
    }

    @PostMapping("/blogs/search")
    public String search(SearchBlog searchBlog, Model model,
                         @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        blogService.transformRecommend(searchBlog);
        //使用动态sql
        List<BlogInfo> blogBySearch = blogService.getBlogBySearch(searchBlog);
        PageHelper.startPage(pageNum, 5);
        PageInfo<BlogInfo> pageInfo = new PageInfo<>(blogBySearch);
        model.addAttribute("pageInfo", pageInfo);
        setTypeAndTag(model);
        model.addAttribute("message", "查询成功");
        return "admin/blogs";
    }

    //将数据回返编辑页面
    @GetMapping("/blogs/{id}/input")
    public String toUpdate(@PathVariable Long id, Model model) {
        ShowBlog blogById = blogService.getBlogById(id);
        List<Type> allType = typeService.getTypesForPage();
        List<Tag> allTag = tagService.getTagsForPage();
        model.addAttribute("blog", blogById);
        model.addAttribute("types", allType);
        model.addAttribute("tags", allTag);
        return "admin/blogs-update";
    }

    @PostMapping("/blogs/update")
    public String editPost(ShowBlog showBlog, RedirectAttributes attributes) {
        blogService.updateBlog(showBlog);
        attributes.addFlashAttribute("message", "修改成功");
        return "redirect:/admin/blogs";
    }

    @GetMapping("/blogs/{id}/delete")
    public String deleteBlog(@PathVariable("id") Long id, RedirectAttributes attributes) {
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "修改成功");
        return "redirect:/admin/blogs";
    }

}
