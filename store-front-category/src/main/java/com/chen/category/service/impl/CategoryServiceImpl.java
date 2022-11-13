package com.chen.category.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.category.mapper.CategoryMapper;
import com.chen.category.service.CategoryService;
import com.chen.client.ProductClient;
import com.chen.param.PageParam;
import com.chen.param.ProductHotParam;
import com.chen.pojo.Category;
import com.chen.untils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    @Resource
    private ProductClient productClient;
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

    @Override
    public R listPage(PageParam param) {
        Page<Category> page = lambdaQuery().page(new Page<>(param.getCurrentPage(), param.getPageSize()));
        R ok = R.ok("查询成功", page.getRecords(), page.getTotal());
        log.info("CategoryServiceImpl.listPage业务结束，结果:{}",ok);
        return ok;
    }

    @Override
    public R adminSave(Category category) {
        Long count = lambdaQuery().eq(Category::getCategoryName, category.getCategoryName()).count();
        if (count!=0){
            return R.fail("类别已经存在，添加失败");
        }
        boolean save = save(category);
        log.info("CategoryServiceImpl.adminSave业务结束，结果:{}",save);
        return R.ok("类别添加成功");
    }

    @Override
    public R adminRemove(Integer categoryId) {
        Long count = productClient.adminCount(categoryId);
        if (count!=0) {
            return R.fail("类别删除失败，有"+count+"商品正在引用！");
        }
        boolean removeById = removeById(categoryId);
        log.info("CategoryServiceImpl.adminRemove业务结束，结果:{}",removeById);
        return R.ok("类别数据删除成功");
    }

    @Override
    public R update(Category category) {
        Long count = lambdaQuery().eq(Category::getCategoryName, category.getCategoryName()).count();
        if (count>0){
            return R.fail("类别已经存在，修改失败");
        }
        boolean updateById = updateById(category);
        log.info("CategoryServiceImpl.update业务结束，结果:{}",updateById);
        return R.ok("修改成功");
    }
}
