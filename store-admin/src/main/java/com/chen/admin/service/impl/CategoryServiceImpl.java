package com.chen.admin.service.impl;

import com.chen.admin.service.CategoryService;
import com.chen.client.CategoryClient;
import com.chen.param.PageParam;
import com.chen.pojo.Category;
import com.chen.untils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 类别服务impl
 *
 * @author CHEN
 * @date 2022/11/13
 */
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryClient categoryClient;


    @Override
    @Cacheable(value = "list.category",key = "#param.currentPage+'-'+#param.pageSize")
    public R listPage(PageParam param) {
        R r = categoryClient.adminPageList(param);
        log.info("CategoryServiceImpl.listPage业务结束，结果:{}",r);
        return r;
    }

    @Override
    @CacheEvict(value = "list.category",allEntries = true)
    public R save(Category category) {
        R r = categoryClient.adminSave(category);
        log.info("CategoryServiceImpl.save业务结束，结果:{}",r);
        return r;
    }

    @Override
    @CacheEvict(value = "list.category",allEntries = true)
    public R remove(Integer categoryId) {
        R r = categoryClient.adminRemove(categoryId);
        log.info("CategoryServiceImpl.remove业务结束，结果:{}",r);
        return r;
    }

    @Override
    @CacheEvict(value = "list.category",allEntries = true)
    public R update(Category category) {
        R r = categoryClient.adminUpdate(category);
        log.info("CategoryServiceImpl.update业务结束，结果:{}",r);
        return r;
    }
}
