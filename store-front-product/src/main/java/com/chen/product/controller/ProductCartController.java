package com.chen.product.controller;

import com.chen.param.ProductCollectParam;
import com.chen.param.ProductIdParam;
import com.chen.pojo.Product;
import com.chen.product.service.ProductService;
import com.chen.untils.R;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * 产品车控制器
 *
 * @author CHEN
 * @date 2022/11/12
 */
@RestController
@RequestMapping("/product")
public class ProductCartController {
    @Resource
    private ProductService productService;

    @PostMapping("/cart/detail")
    public Product productDetail(@RequestBody @Validated ProductIdParam productIdParam, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return null;
        }
        R detail = productService.detail(productIdParam);
        Product product= (Product) detail.getData();
        return product;
    }

    @PostMapping("/cart/list")
    public List<Product> cartList(@RequestBody @Validated ProductCollectParam productCollectParam,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return Collections.emptyList();
        }
        return productService.listByIds(productCollectParam.getProductIds());
    }
}
