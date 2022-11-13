package com.chen.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.pojo.Order;
import com.chen.vo.AdminOrderVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 顺序映射器
 *
 * @author CHEN
 * @date 2022/11/13
 */
public interface OrderMapper extends BaseMapper<Order> {
    /**
     * 选择管理订单
     *
     * @param offset 抵消
     * @param number 数量
     * @return {@link List}<{@link AdminOrderVo}>
     */
    List<AdminOrderVo> selectAdminOrders(@Param("offset")int offset,@Param("number") int number);
}
