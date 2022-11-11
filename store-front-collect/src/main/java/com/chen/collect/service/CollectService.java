package com.chen.collect.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.pojo.Collect;
import com.chen.untils.R;

/**
 * 收集服务
 *
 * @author CHEN
 * @date 2022/11/12
 */
public interface CollectService extends IService<Collect> {

    /**
     * 保存收集
     *
     * @param collect 收集
     * @return {@link R}
     */
    R saveCollect(Collect collect);

    /**
     * 得到收集名单
     *
     * @param collect 收集
     * @return {@link R}
     */
    R getCollectList(Collect collect);

    /**
     * 删除收集
     *
     * @param collect 收集
     * @return {@link R}
     */
    R removeCollect(Collect collect);
}
