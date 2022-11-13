package com.chen.collect.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.client.ProductClient;
import com.chen.collect.mapper.CollectMapper;
import com.chen.collect.service.CollectService;
import com.chen.param.ProductCollectParam;
import com.chen.pojo.Collect;
import com.chen.pojo.Product;
import com.chen.untils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 收集服务impl
 *
 * @author CHEN
 * @date 2022/11/12
 */
@Service
@Slf4j
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect> implements CollectService {

    @Resource
    private ProductClient productClient;

    @Override
    public R saveCollect(Collect collect) {
        //查询是否存在
        Long count = lambdaQuery().eq(Collect::getUserId, collect.getUserId())
                .eq(Collect::getProductId, collect.getProductId())
                .count();
        //已经添加
        if (count != 0) {
            return R.fail("已经添加，无需二次添加");
        }
        //不存在 进行添加
        collect.setCollectTime(System.currentTimeMillis());
        boolean isSuccess = this.save(collect);
        log.info("CollectServiceImpl.saveCollect业务结束，结果:{}", collect);
        return R.ok("收藏添加成功！");
    }

    @Override
    public R getCollectList(Collect collect) {
        //查询数据库
        List<Collect> list = lambdaQuery().eq(Collect::getUserId, collect.getUserId()).select(Collect::getProductId).list();
        List<Integer> productIds = list.stream().map(Collect::getProductId).collect(Collectors.toList());
        //远程调用 获取商品信息
        ProductCollectParam productCollectParam = new ProductCollectParam();
        productCollectParam.setProductIds(productIds);
        R result = productClient.getProductListByProductIds(productCollectParam);
        log.info("CollectServiceImpl.getCollectList业务结束，结果:{}", result);
        return result;
    }

    @Override
    public R removeCollect(Collect collect) {
        LambdaQueryWrapper<Collect> collectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        collectLambdaQueryWrapper.eq(Collect::getUserId, collect.getUserId());
        collectLambdaQueryWrapper.eq(Collect::getProductId, collect.getProductId());
        int rows = baseMapper.delete(collectLambdaQueryWrapper);
        log.info("CollectServiceImpl.removeCollect业务结束，结果:{}",rows);
        return R.ok("收藏移除成功");
    }

    @Override
    public R remove(Integer productId) {
        boolean remove = removeById(productId);
        log.info("CollectServiceImpl.remove业务结束，结果:{}",remove);
        return R.ok("收藏商品删除成功");
    }
}
