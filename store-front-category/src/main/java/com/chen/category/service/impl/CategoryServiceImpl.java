package com.chen.category.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.category.mapper.CategoryMapper;
import com.chen.category.service.CategoryService;
import com.chen.param.ProductHotParam;
import com.chen.pojo.Category;
import com.chen.untils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 类别服务impl
 *
 * @author CHEN
 * @date 2022/11/11
 */
@Service
@Slf4j
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Override
    public R getCategoryByName(String categoryName) {
        //数据库查询
        Category category = lambdaQuery().eq(Category::getCategoryName, categoryName).one();
        //返回结果
        if (Objects.isNull(category)){
            log.info("CategoryServiceImpl.getCategoryByName业务结束，结果:{}","类别查询失败");
            return R.fail("查询失败");
        }
        log.info("CategoryServiceImpl.getCategoryByName业务结束，结果:{}","类别查询成功");
        return R.ok("查询成功",category);
    }

    @Override
    public R hotsCategory(ProductHotParam productHotParam) {
        //数据库查询
        List<String> categoryNameList = productHotParam.getCategoryName();
        List<Category> categoryList = lambdaQuery().in(Category::getCategoryName, categoryNameList).list();
        //封装结果
        List<Integer> categoryIdList = categoryList.stream().map(Category::getCategoryId).collect(Collectors.toList());
        R ok = R.ok("查询成功", categoryIdList);
        log.info("CategoryServiceImpl.hotsCategory业务结束，结果:{}",ok);
        return ok;
    }
}
