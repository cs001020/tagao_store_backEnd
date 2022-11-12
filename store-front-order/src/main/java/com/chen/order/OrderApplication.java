package com.chen.order;

import com.chen.client.ProductClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 订单应用程序
 *
 * @author CHEN
 * @date 2022/11/13
 */
@SpringBootApplication
@MapperScan("com.chen.order.mapper")
@EnableFeignClients(clients = {ProductClient.class})
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class,args);
    }
}