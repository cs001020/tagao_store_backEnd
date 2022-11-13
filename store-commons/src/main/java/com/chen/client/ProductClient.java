package com.chen.client;

import com.chen.param.ProductCollectParam;
import com.chen.param.ProductIdParam;
import com.chen.param.ProductSaveParam;
import com.chen.pojo.Product;
import com.chen.untils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 产品客户端
 *
 * @author CHEN
 * @date 2022/11/11
 */
@FeignClient("product-service")
public interface ProductClient {

    /**
     * 获取所用商品信息（用于搜索服务）
     *
     * @return {@link List}<{@link Product}>
     */
    @GetMapping("/product/list")
    List<Product> getAllProduct();

    /**
     * 根据产品id产品列表
     *
     * @param productCollectParam 收集产品参数
     * @return {@link R}
     */
    @PostMapping("/product/collect/list")
    R getProductListByProductIds(@RequestBody ProductCollectParam productCollectParam);

    @PostMapping("/product/cart/detail")
    Product productDetail(@RequestBody ProductIdParam productIdParam);

    @PostMapping("/product/cart/list")
    List<Product> cartList(@RequestBody ProductCollectParam productCollectParam);

    @PostMapping("/product/admin/count")
    Long adminCount(@RequestBody Integer categoryId);

    @PostMapping("/product/admin/save")
    R adminSave(@RequestBody ProductSaveParam productSaveParam);

    @PostMapping("/product/admin/update")
    R adminUpdate(@RequestBody Product product);

    @PostMapping("/product/admin/remove")
    R adminRemove(@RequestBody Integer productId);
}
