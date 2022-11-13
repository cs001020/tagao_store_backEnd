package com.chen.client;

import com.chen.param.PageParam;
import com.chen.param.ProductHotParam;
import com.chen.pojo.Category;
import com.chen.untils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 类别远程调用
 *
 * @author CHEN
 * @date 2022/11/11
 */
@FeignClient("category-service")
public interface CategoryClient {
    @GetMapping("/category/promo/{categoryName}")
    R getCategoryByName(@PathVariable("categoryName")String categoryName);

    @PostMapping("/category/hots")
    R hots(@RequestBody ProductHotParam productHotParam);

    @GetMapping("/category/list")
    R list();

    @PostMapping("/category/admin/list")
    R adminPageList(@RequestBody PageParam param);

    @PostMapping("/category/admin/save")
    R adminSave(@RequestBody Category category);

    @PostMapping("/category/admin/remove")
    R adminRemove(@RequestBody Integer categoryId);

    @PostMapping("/category/admin/update")
    R adminUpdate(@RequestBody Category category);
}
