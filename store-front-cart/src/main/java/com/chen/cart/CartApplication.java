package com.chen.cart;

import com.chen.client.ProductClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 购物车应用程序
 *
 * @author CHEN
 * @date 2022/11/12
 */
@MapperScan("com.chen.cart.mapper")
@SpringBootApplication
@EnableFeignClients(clients = {ProductClient.class})
public class CartApplication {
    public static void main(String[] args) {
        SpringApplication.run(CartApplication.class,args);
    }
}