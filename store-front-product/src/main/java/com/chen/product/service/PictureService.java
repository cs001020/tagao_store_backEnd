package com.chen.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.pojo.Picture;

/**
 * 图片服务
 *
 * @author CHEN
 * @date 2022/11/11
 */
public interface PictureService extends IService<Picture> {
    /**
     * 删除产品id
     *
     * @param productId 产品id
     */
    void removeByProductId(Integer productId);
}
