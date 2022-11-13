package com.chen.product.controller;

import com.chen.pojo.Product;
import com.chen.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 产品管理控制器
 *
 * @author CHEN
 * @date 2022/11/13
 */
@RestController
@RequestMapping("/product")
@Slf4j
public class ProductAdminController {
    @Resource
    private ProductService productService;

    @PostMapping("/admin/count")
    public Long adminCount(@RequestBody Integer categoryId){
        Long count = productService.lambdaQuery().eq(Product::getCategoryId, categoryId).count();
        log.info("ProductAdminController.adminCount业务结束，结果:{}",count);
        return count;
    }
}
