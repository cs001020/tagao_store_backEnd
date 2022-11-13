package com.chen.admin.controller;

import com.chen.admin.service.ProductService;
import com.chen.admin.utils.FileUtils;
import com.chen.param.ProductSaveParam;
import com.chen.param.ProductSearchParam;
import com.chen.pojo.Product;
import com.chen.untils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.UUID;

/**
 * 产品控制器
 *
 * @author CHEN
 * @date 2022/11/13
 */
@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

    @Resource
    private ProductService productService;

    @Resource
    private FileUtils fileUtils;

    @Value("${kodo.baseUrl}")
    private String ossBaseUrl;

    @GetMapping("/list")
    public R adminList(ProductSearchParam param) {
        return productService.search(param);
    }

    @PostMapping("/upload")
    public R adminUpload(MultipartFile img) {
        String fileName = UUID.randomUUID().toString()+"-"+img.getOriginalFilename();
        //TODO 上传文件
        try {
            fileUtils.fileUpload(fileName, img.getInputStream());
        } catch (Exception e) {
            log.info("ProductController.adminUpload业务结束，结果:{}", e);
            return R.fail("文件上传出现异常");
        }
        return R.ok("上传成功",ossBaseUrl+fileName);
    }

    @PostMapping("/save")
    public R adminSave(ProductSaveParam param) {
        return productService.save(param);
    }

    @PostMapping("/update")
    public R adminUpdate(Product product) {
        return productService.update(product);
    }

    @PostMapping("/remove")
    public R adminRemove(Integer productId) {
        return productService.remove(productId);
    }

}
