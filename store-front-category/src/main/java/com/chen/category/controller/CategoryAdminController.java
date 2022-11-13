package com.chen.category.controller;

import com.chen.category.service.CategoryService;
import com.chen.param.PageParam;
import com.chen.pojo.Category;
import com.chen.untils.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 类别管理控制器
 *
 * @author CHEN
 * @date 2022/11/13
 */
@RestController
@RequestMapping("/category")
public class CategoryAdminController {
    @Resource
    private CategoryService categoryService;

    @PostMapping("/admin/list")
    public R listPage(@RequestBody PageParam param){
        return categoryService.listPage(param);
    }

    @PostMapping("/admin/save")
    public R adminSave(@RequestBody Category category){
        return categoryService.adminSave(category);
    }

    @PostMapping("/admin/remove")
    public R adminRemove(@RequestBody Integer categoryId){
        return categoryService.adminRemove(categoryId);
    }

    @PostMapping("/admin/update")
    public R adminUpdate(@RequestBody Category category){
        return categoryService.update(category);
    }
}
