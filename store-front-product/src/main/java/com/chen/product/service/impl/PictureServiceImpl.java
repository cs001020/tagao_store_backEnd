package com.chen.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.pojo.Picture;
import com.chen.product.mapper.PictureMapper;
import com.chen.product.service.PictureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 图片服务impl
 *
 * @author CHEN
 * @date 2022/11/11
 */
@Service
@Slf4j
public class PictureServiceImpl extends ServiceImpl<PictureMapper, Picture> implements PictureService {
}
