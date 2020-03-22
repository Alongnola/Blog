package com.jwm.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jwm.blog.pojo.Type;
import com.jwm.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String list(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum, 5);
        List<Type> allType = typeService.getTypesForPage();
        // 得到分页的结果
        PageInfo<Type> pageInfo = new PageInfo<>(allType);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/types";
    }

    // 跳转至新增页面
    @GetMapping("/types/input")
    public String addInput() {
        return "admin/types-input";
    }

    @PostMapping("types/add")
    public String addType(Type type, RedirectAttributes attributes) {
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null) {
            attributes.addFlashAttribute("message", "该类型已存在，请重新添加分类！");
            return "redirect:/admin/types/input";
        }
        typeService.saveType(type);
        return "redirect:/admin/types";
    }

    //删除
    @GetMapping("/types/{id}/delete")
    public String deleteType(@PathVariable Long id, RedirectAttributes attributes) {
        typeService.deleteType(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/types";
    }

    //跳转至修改页面
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        Type type = typeService.getTypeById(id);
        // 将分类信息推送至前端
        model.addAttribute("type", type);
        return "admin/types-update";
    }

    @PostMapping("types/update")
    public String updateType(Type type, RedirectAttributes attributes) {
        Type type1 = typeService.getTypeByName(type.getName());
        if(type1 != null){
            attributes.addFlashAttribute("message", "该类型已存在，请重新添加分类！");
            return "admin/types-input";
        }
        typeService.updateType(type.getId(), type);
        return "redirect:/admin/types";
    }

}
