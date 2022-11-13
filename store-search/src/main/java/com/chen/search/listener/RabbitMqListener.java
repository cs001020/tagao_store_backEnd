package com.chen.search.listener;

import com.chen.doc.ProductDoc;
import com.chen.pojo.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * RabbitMq监听器
 *
 * @author CHEN
 * @date 2022/11/11
 */
@Component
@Deprecated
public class RabbitMqListener {

    @Resource
    private RestHighLevelClient restHighLevelClient;

    /**
     * 监听 并且插入数据
     *
     * @param product 产品
     */
    @RabbitListener(
            bindings = {
                    @QueueBinding(
                            value = @Queue(name = "insert.queue"),
                            exchange = @Exchange(value = "topic.ex"),
                            key = "product.insert"
                    )
            }
    )
    public void insert(Product product) throws IOException {
        IndexRequest indexRequest = new IndexRequest("product").id(product.getProductId().toString());
        ProductDoc productDoc = new ProductDoc(product);
        ObjectMapper objectMapper = new ObjectMapper();
        indexRequest.source(objectMapper.writeValueAsString(productDoc), XContentType.JSON);
        restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
    }

    @RabbitListener(
            bindings = {
                    @QueueBinding(
                            value = @Queue(name = "remove.queue"),
                            exchange = @Exchange(value = "topic.ex"),
                            key = "product.remove"
                    )
            }
    )
    public void remove(Integer productId) throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest("product").id(productId.toString());
        restHighLevelClient.delete(deleteRequest,RequestOptions.DEFAULT);
    }
}
