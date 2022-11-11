package com.chen.collect;

import com.chen.client.ProductClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 收集应用程序
 *
 * @author CHEN
 * @date 2022/11/12
 */
@SpringBootApplication
@MapperScan("com.chen.collect.mapper")
@EnableFeignClients(clients = {ProductClient.class})
public class CollectApplication {
    public static void main(String[] args) {
        SpringApplication.run(CollectApplication.class,args);
    }
}