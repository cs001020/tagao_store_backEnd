package com.chen.carousel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


/**
 * 轮播图启动类
 *
 * @author CHEN
 * @date 2022/11/10
 */
@SpringBootApplication
@MapperScan("com.chen.carousel.mapper")
@EnableCaching
public class CarouselApplication {
    public static void main(String[] args) {
        SpringApplication.run(CarouselApplication.class,args);
    }
}
