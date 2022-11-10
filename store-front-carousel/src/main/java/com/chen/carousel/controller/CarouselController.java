package com.chen.carousel.controller;

import com.chen.carousel.service.CarouselService;
import com.chen.untils.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 轮播图控制器
 *
 * @author CHEN
 * @date 2022/11/10
 */
@RestController
@RequestMapping("/carousel")
public class CarouselController {
    @Resource
    private CarouselService carouselService;

    /**
     * 仅返回优先级最高的六个
     *
     * @return {@link R}
     */
    @PostMapping("/list")
    public R getCarouselList(){
        return carouselService.getCarouselList();
    }
}
