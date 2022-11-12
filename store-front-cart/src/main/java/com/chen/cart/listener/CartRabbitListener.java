package com.chen.cart.listener;

import com.chen.cart.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 购物车rabbitMq监听器
 *
 * @author CHEN
 * @date 2022/11/13
 */
@Component
@Slf4j
public class CartRabbitListener {
    @Resource
    private CartService cartService;

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(name = "clear.queue"),
                    exchange = @Exchange(value = "topic.ex"),
                    key = "clear.cart"
            )
    )
    public void clearCart(List<Integer> cartIds){
        cartService.removeBatchByIds(cartIds);
        log.info("CartRabbitListener.clearCart业务结束，结果:{}","购物车清除完毕");
    }
}
