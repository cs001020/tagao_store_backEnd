package com.chen.carousel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.carousel.mapper.CarouselMapper;
import com.chen.carousel.service.CarouselService;
import com.chen.pojo.Carousel;
import com.chen.untils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 轮播图服务impl
 *
 * @author CHEN
 * @date 2022/11/10
 */
@Service
@Slf4j
public class CarouselServiceImpl extends ServiceImpl<CarouselMapper,Carousel> implements CarouselService {
    @Override
    public R getCarouselList() {
        //查询数据库 按照优先级降序排序
        List<Carousel> list = lambdaQuery().orderByDesc(Carousel::getPriority).list();
        //取出前六条
        List<Carousel> carouselList = list.stream().limit(6).collect(Collectors.toList());
        //返回结果
        log.info("CarouselServiceImpl.getCarouselList业务结束，结果:{}",carouselList);
        return R.ok(carouselList);
    }
}
