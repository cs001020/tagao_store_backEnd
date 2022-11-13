package com.chen.admin.controller;

import com.chen.admin.service.CategoryService;
import com.chen.param.PageParam;
import com.chen.pojo.Category;
import com.chen.untils.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 类别控制器
 *
 * @author CHEN
 * @date 2022/11/13
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @GetMapping("/list")
    public R pageList(PageParam param){
        return categoryService.listPage(param);
    }

    @PostMapping("/save")
    public R save(Category category){
        return categoryService.save(category);
    }

    @PostMapping("/remove")
    public R remove(Integer categoryId){
        return categoryService.remove(categoryId);
    }

    @PostMapping("/update")
    public R remove(Category category){
        return categoryService.update(category);
    }
}
