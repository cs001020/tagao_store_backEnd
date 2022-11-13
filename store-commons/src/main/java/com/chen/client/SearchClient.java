package com.chen.client;

import com.chen.param.ProductSearchParam;
import com.chen.pojo.Product;
import com.chen.untils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

/**
 * 搜索客户
 *
 * @author CHEN
 * @date 2022/11/11
 */
@FeignClient("search-service")
public interface SearchClient {
    @PostMapping("/search/product")
    R searchProduct(@RequestBody ProductSearchParam productSearchParam);

    @PostMapping("/search/save")
    R save(@RequestBody Product product);

    @PostMapping("/search/remove")
    R remove(@RequestBody Integer productId);
}
