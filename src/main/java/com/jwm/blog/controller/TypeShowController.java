package com.jwm.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jwm.blog.dto.FirstPageBlog;
import com.jwm.blog.pojo.Type;
import com.jwm.blog.service.BlogService;
import com.jwm.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, @PathVariable Long id, Model model) {
        List<Type> types = typeService.getTypesByBlog();
        //默认推荐首个类型的所有微博
        if (id == -1) {
            id = types.get(0).getId();
        }
        model.addAttribute("types", types);
        List<FirstPageBlog> blogs = blogService.getByTypeId(id);
        PageHelper.startPage(pageNum, 100);
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("activeTypeId", id);
        return "types";
    }

}
