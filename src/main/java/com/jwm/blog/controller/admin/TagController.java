package com.jwm.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jwm.blog.pojo.Tag;
import com.jwm.blog.pojo.Type;
import com.jwm.blog.service.TagService;
import com.jwm.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public String list(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum, 5);
        List<Tag> allType = tagService.getTagsForPage();
        // 得到分页的结果
        PageInfo<Tag> pageInfo = new PageInfo<>(allType);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/tags";
    }

    // 跳转至新增页面
    @GetMapping("/tags/input")
    public String addInput() {
        return "admin/tags-input";
    }

    @PostMapping("tags/add")
    public String addType(Tag tag, RedirectAttributes attributes) {
        Tag tag1 = tagService.getTagByName(tag.getName());
        if (tag1 != null) {
            attributes.addFlashAttribute("message", "该标签已存在，请重新添加标签！");
            return "redirect:/admin/tags/input";
        }
        tagService.saveTag(tag);
        return "redirect:/admin/tags";
    }

    //删除
    @GetMapping("/tags/{id}/delete")
    public String deleteType(@PathVariable Long id, RedirectAttributes attributes) {
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/tags";
    }

    //跳转至修改页面
    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        Tag tag = tagService.getTagById(id);
        // 将分类信息推送至前端
        model.addAttribute("tag", tag);
        return "admin/tags-update";
    }

    @PostMapping("tags/update")
    public String updateType(Tag tag, RedirectAttributes attributes) {
        Tag tag1 = tagService.getTagByName(tag.getName());
        if(tag1 != null){
            attributes.addFlashAttribute("message", "该标签已存在，请重新添加标签！");
            return "admin/types-input";
        }
        tagService.updateTag(tag.getId(), tag);
        return "redirect:/admin/tags";
    }

}
