package com.chen.client;

import com.chen.pojo.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

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
}
