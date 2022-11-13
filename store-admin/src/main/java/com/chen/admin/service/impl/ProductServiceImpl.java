package com.chen.admin.service.impl;

import com.chen.admin.service.ProductService;
import com.chen.client.ProductClient;
import com.chen.client.SearchClient;
import com.chen.param.ProductSaveParam;
import com.chen.param.ProductSearchParam;
import com.chen.pojo.Product;
import com.chen.untils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 产品服务impl
 *
 * @author CHEN
 * @date 2022/11/13
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Resource
    private SearchClient searchClient;
    @Resource
    private ProductClient productClient;

    @Override
    public R search(ProductSearchParam param) {
        R r = searchClient.searchProduct(param);
        log.info("ProductServiceImpl.adminList业务结束，结果:{}",r);
        return r;
    }

    @Override
    public R save(ProductSaveParam param) {
        R r = productClient.adminSave(param);
        log.info("ProductServiceImpl.save业务结束，结果:{}",r);
        return r;
    }

    @Override
    public R update(Product param) {
        R r = productClient.adminUpdate(param);
        log.info("ProductServiceImpl.update业务结束，结果:{}",r);
        return r;
    }

    @Override
    public R remove(Integer productId) {
        R r = productClient.adminRemove(productId);
        log.info("ProductServiceImpl.remove业务结束，结果:{}",r);
        return r;
    }
}
