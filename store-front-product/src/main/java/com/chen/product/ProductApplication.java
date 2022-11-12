package com.chen.product;

import com.chen.client.CategoryClient;
import com.chen.client.SearchClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 商品启动类
 *
 * @author CHEN
 * @date 2022/11/10
 */
@SpringBootApplication
@MapperScan("com.chen.product.mapper")
@EnableFeignClients(clients = {CategoryClient.class, SearchClient.class})
@EnableCaching
public class ProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class,args);
    }
}