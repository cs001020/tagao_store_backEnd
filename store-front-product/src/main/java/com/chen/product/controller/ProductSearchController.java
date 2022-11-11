package com.chen.product.controller;

import com.chen.pojo.Product;
import com.chen.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 产品控制器(搜索服务调用)
 *
 * @author CHEN
 * @date 2022/11/11
 */
@Slf4j
@RestController
@RequestMapping("/product")
public class ProductSearchController {

    @Resource
    private ProductService productService;

    @GetMapping("/list")
    public List<Product> getAllProduct(){
        List<Product> productList = productService.list();
        log.info("ProductSearchController.getAllProduct业务结束，结果:{}",productList.size());
        return productList;
    }
}
