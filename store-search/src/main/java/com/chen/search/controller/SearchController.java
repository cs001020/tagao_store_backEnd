package com.chen.search.controller;

import com.chen.param.ProductSearchParam;
import com.chen.pojo.Product;
import com.chen.search.service.SearchService;
import com.chen.untils.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 搜索控制器
 *
 * @author CHEN
 * @date 2022/11/11
 */
@RestController
@RequestMapping("/search")
public class SearchController {
    @Resource
    private SearchService searchService;

    @PostMapping("/product")
    public R searchProduct(@RequestBody ProductSearchParam productSearchParam){
        return searchService.searchProduct(productSearchParam);
    }
    @PostMapping("/save")
    public R save(@RequestBody Product product) throws IOException {
        return searchService.save(product);
    }

    @PostMapping("/remove")
    public R remove(@RequestBody Integer productId) throws IOException {
        return searchService.remove(productId);
    }
}
