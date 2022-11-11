package com.chen.category.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.chen.category.service.CategoryService;
import com.chen.param.ProductHotParam;
import com.chen.untils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 类别控制器
 *
 * @author CHEN
 * @date 2022/11/11
 */
@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @GetMapping("/promo/{categoryName}")
    public R getCategoryByName(@PathVariable("categoryName")String categoryName){
        if (StringUtils.isEmpty(categoryName)){
            return R.fail("非法参数！");
        }
        return categoryService.getCategoryByName(categoryName);
    }
    @PostMapping("/hots")
    public R hotsCategory(@RequestBody @Validated ProductHotParam productHotParam, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return R.fail("查询失败！");
        }
        return categoryService.hotsCategory(productHotParam);
    }

    @GetMapping("/list")
    public R list(){
        log.info("CategoryController.list业务结束，结果:{}","全部类别信息查询成功");
        return R.ok("查询成功",categoryService.list());
    }
}


