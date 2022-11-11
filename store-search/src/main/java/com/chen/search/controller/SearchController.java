package com.chen.search.controller;

import com.chen.param.ProductSearchParam;
import com.chen.search.service.SearchService;
import com.chen.untils.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
}
