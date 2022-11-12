package com.chen.product.listener;

import com.chen.product.service.ProductService;
import com.chen.to.OrderToProduct;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 产品RabbitMq监听器
 *
 * @author CHEN
 * @date 2022/11/13
 */
@Component
public class ProductRabbitMqListener {

    @Resource
    private ProductService productService;

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(name = "sub.queue"),
                    exchange = @Exchange(value ="topic.ex" ),
                    key = "sub.number"
            )
    )
    public void sumNumber(List<OrderToProduct> orderToProducts){
        productService.subNumber(orderToProducts);
    }
}
