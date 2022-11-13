package com.chen.admin;

import com.chen.client.*;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 管理应用程序
 *
 * @author CHEN
 * @date 2022/11/13
 */
@SpringBootApplication
@MapperScan("com.chen.admin.mapper")
@EnableCaching
@EnableFeignClients(clients = {CategoryClient.class, UserClient.class, SearchClient.class, ProductClient.class, OrderClient.class})
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class,args);
    }
}