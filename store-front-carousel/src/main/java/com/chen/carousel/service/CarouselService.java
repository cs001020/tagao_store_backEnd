package com.chen.carousel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.pojo.Carousel;
import com.chen.untils.R;

/**
 * 轮播图服务
 *
 * @author CHEN
 * @date 2022/11/10
 */
public interface CarouselService extends IService<Carousel> {
    /**
     * 获得轮播图列表，仅优先级最高的六个
     *
     * @return {@link R}
     */
    R getCarouselList();
}
