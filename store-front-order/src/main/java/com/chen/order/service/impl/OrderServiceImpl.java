package com.chen.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.client.ProductClient;
import com.chen.order.mapper.OrderMapper;
import com.chen.order.service.OrderService;
import com.chen.param.CartListParam;
import com.chen.param.OrderParam;
import com.chen.param.PageParam;
import com.chen.param.ProductCollectParam;
import com.chen.pojo.Order;
import com.chen.pojo.Product;
import com.chen.to.OrderToProduct;
import com.chen.untils.R;
import com.chen.vo.AdminOrderVo;
import com.chen.vo.CartVo;
import com.chen.vo.OrderVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 订单服务
 *
 * @author CHEN
 * @date 2022/11/13
 */
@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Resource
    private RabbitTemplate rabbitTemplate;
    @Resource
    private ProductClient productClient;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public R saveOrder(OrderParam orderParam) {
        //购物车数据转订单数据
        //准备数据
        List<Integer> cartIds=new ArrayList<>();
        List<OrderToProduct> orderToProducts=new ArrayList<>();
        List<Order> orderList=new ArrayList<>();
        //生成数据
        Integer userId = orderParam.getUserId();
        long currentTimeMillis = System.currentTimeMillis();
        for (CartVo product : orderParam.getProducts()) {
            //购物车项id
            cartIds.add(product.getId());
            //给商品服务发送消息
            OrderToProduct orderToProduct = new OrderToProduct();
            orderToProduct.setProductId(product.getProductID());
            orderToProduct.setNum(product.getNum());
            orderToProducts.add(orderToProduct);
            //订单数据
            Order order = new Order();
            order.setOrderId(currentTimeMillis);
            order.setOrderTime(currentTimeMillis);
            order.setUserId(userId);
            order.setProductId(product.getProductID());
            order.setProductPrice(product.getPrice());
            order.setProductNum(product.getNum());
            orderList.add(order);
        }
        //进行订单数据批量插入
        saveBatch(orderList);
        //购物车库存修改消息
        rabbitTemplate.convertAndSend("topic.ex","clear.cart",cartIds);
        //商品库存修改消息
        rabbitTemplate.convertAndSend("topic.ex","sub.number",orderToProducts);
        return R.ok("结算成功");
    }

    @Override
    public R orderList(CartListParam cartListParam) {
        //查询用户订单信息
        List<Order> orderList = lambdaQuery().eq(Order::getUserId, cartListParam.getUserId()).list();
        //分组
        Map<Long, List<Order>> collect = orderList.stream().collect(Collectors.groupingBy(Order::getOrderId));
        //查询商品数据
        List<Integer> productIds = orderList.stream().map(Order::getProductId).collect(Collectors.toList());
        ProductCollectParam productCollectParam = new ProductCollectParam();
        productCollectParam.setProductIds(productIds);
        List<Product> products = productClient.cartList(productCollectParam);
        Map<Integer, Product> collect1 = products.stream().collect(Collectors.toMap(Product::getProductId, product -> product));
        //封装 返回
        List<List<OrderVo>> res=new ArrayList<>();
        for (List<Order> value : collect.values()) {
            List<OrderVo> orderVoList=new ArrayList<>();
            for (Order order : value) {
                OrderVo orderVo = new OrderVo();
                BeanUtils.copyProperties(order,orderVo);
                Product product = collect1.get(orderVo.getProductId());
                orderVo.setProductName(product.getProductName());
                orderVo.setProductPicture(product.getProductPicture());
                orderVoList.add(orderVo);
            }
            res.add(orderVoList);
        }
        log.info("OrderServiceImpl.orderList业务结束，结果:{}",res);
        return R.ok("订单数据获取成功",res);
    }

    @Override
    public R removeCheck(Integer productId) {
        Long count = lambdaQuery().eq(Order::getProductId, productId).count();
        if (count!=0){
            return R.fail("存在订单引用，无法删除");
        }
        return R.ok("无订单引用");
    }

    @Override
    public R adminList(PageParam pageParam) {

        int offset = (pageParam.getCurrentPage()-1)*pageParam.getPageSize();
        int number = pageParam.getPageSize();

        //查询数量
        Long total = baseMapper.selectCount(null);
        //自定义查询
        List<AdminOrderVo> adminOrderVoList = baseMapper.selectAdminOrders(offset,number);


        return R.ok("查询成功",adminOrderVoList,total);
    }
}
