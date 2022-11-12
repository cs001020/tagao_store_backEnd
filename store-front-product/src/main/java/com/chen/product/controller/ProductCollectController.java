package com.chen.product.controller;

import com.chen.param.ProductCollectParam;
import com.chen.product.service.ProductService;
import com.chen.untils.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * 产品采集控制器
 *
 * @author CHEN
 * @date 2022/11/12
 */
@RestController
@RequestMapping("/product")
public class ProductCollectController {
    @Resource
    private ProductService productService;

    @PostMapping("/collect/list")
    public R getProductListByProductIds(@RequestBody @Validated ProductCollectParam productCollectParam, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return R.ok("没有商品数据", Collections.emptyList());
        }
        return productService.getProductListByProductIds(productCollectParam);
    }
}
