package com.chen.product.controller;

import com.chen.param.ProductCategoryIdsParams;
import com.chen.param.ProductHotParam;
import com.chen.param.ProductIdParam;
import com.chen.param.ProductPromoParam;
import com.chen.product.service.ProductService;
import com.chen.untils.R;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 产品控制器
 *
 * @author CHEN
 * @date 2022/11/11
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Resource
    private ProductService productService;

    @PostMapping("/promo")
    public R promo(@RequestBody @Validated ProductPromoParam productPromoParam, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return R.fail("参数异常，查询失败");
        }
        return productService.promo(productPromoParam);
    }

    @PostMapping("/hots")
    public R hots(@RequestBody @Validated ProductHotParam productHotParam,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return R.fail("参数异常，查询失败");
        }
        return productService.hots(productHotParam);
    }

    @PostMapping("/category/list")
    public R categoryList(){
        return productService.categoryList();
    }

    @PostMapping("/bycategory")
    public R byCategoryIds(@RequestBody @Validated ProductCategoryIdsParams params,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return R.fail("参数异常，查询失败");
        }
        return productService.byCategoryIds(params);
    }

    @PostMapping("/all")
    public R all(@RequestBody @Validated ProductCategoryIdsParams params,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return R.fail("参数异常，查询失败");
        }
        return productService.byCategoryIds(params);
    }

    @PostMapping("/detail")
    public R detail(@RequestBody @Validated ProductIdParam param,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return R.fail("参数异常，查询失败!");
        }
        return productService.detail(param);
    }

    @PostMapping("/pictures")
    public R picture(@RequestBody @Validated ProductIdParam param,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return R.fail("参数异常，查询失败!");
        }
        return productService.picture(param);
    }
}
