package com.chen.category;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 类别应用程序
 *
 * @author CHEN
 * @date 2022/11/10
 */
@SpringBootApplication
@MapperScan("com.chen.category.mapper")
public class CategoryApplication {
    public static void main(String[] args) {
        SpringApplication.run(CategoryApplication.class,args);
    }
}