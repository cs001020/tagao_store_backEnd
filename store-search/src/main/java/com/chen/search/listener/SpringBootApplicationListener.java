package com.chen.search.listener;

import com.chen.client.ProductClient;
import com.chen.doc.ProductDoc;
import com.chen.pojo.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 监听springboot程序启动，完成数据同步
 *
 * @author CHEN
 * @date 2022/11/11
 */
@Component
@Slf4j
public class SpringBootApplicationListener implements ApplicationRunner {

    @Resource
    private RestHighLevelClient restHighLevelClient;
    @Resource
    private ProductClient productClient;

    private String inexString="{\n" +
            "  \"mappings\": {\n" +
            "    \"properties\": {\n" +
            "      \"productId\":{\n" +
            "        \"type\": \"integer\"\n" +
            "      },\n" +
            "      \"productName\":{\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_smart\",\n" +
            "        \"copy_to\": \"all\"\n" +
            "      },\n" +
            "      \"categoryId\":{\n" +
            "        \"type\": \"integer\"\n" +
            "      },\n" +
            "      \"productTitle\":{\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_smart\",\n" +
            "        \"copy_to\": \"all\"\n" +
            "      },\n" +
            "      \"productIntro\":{\n" +
            "        \"type\":\"text\",\n" +
            "        \"analyzer\": \"ik_smart\",\n" +
            "        \"copy_to\": \"all\"\n" +
            "      },\n" +
            "      \"productPicture\":{\n" +
            "        \"type\": \"keyword\",\n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"productPrice\":{\n" +
            "        \"type\": \"double\",\n" +
            "        \"index\": true\n" +
            "      },\n" +
            "      \"productSellingPrice\":{\n" +
            "        \"type\": \"double\"\n" +
            "      },\n" +
            "      \"productNum\":{\n" +
            "        \"type\": \"integer\"\n" +
            "      },\n" +
            "      \"productSales\":{\n" +
            "        \"type\": \"integer\"\n" +
            "      },\n" +
            "      \"all\":{\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_max_word\"\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}";;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //判断es中product索引是否存在
        GetIndexRequest getIndexRequest = new GetIndexRequest("product");
        boolean exists = restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        //不存在 创建
        if (!exists){
            CreateIndexRequest createIndexRequest = new CreateIndexRequest("product");
            createIndexRequest.source(inexString, XContentType.JSON);
            restHighLevelClient.indices().create(createIndexRequest,RequestOptions.DEFAULT);

        }
        //删除原来数据
        DeleteByQueryRequest deleteByQueryRequest = new DeleteByQueryRequest("product");
        //全部删除
        deleteByQueryRequest.setQuery(QueryBuilders.matchAllQuery());
        restHighLevelClient.deleteByQuery(deleteByQueryRequest,RequestOptions.DEFAULT);
        //查询全部商品数据
        List<Product> allProduct = productClient.getAllProduct();
        //进行es更新操作（插入数据）
        BulkRequest bulkRequest = new BulkRequest();
        ObjectMapper objectMapper = new ObjectMapper();
        for (Product product : allProduct) {
            ProductDoc productDoc = new ProductDoc(product);
            //用于插入数据
            IndexRequest indexRequest = new IndexRequest("product").id(product.getProductId().toString());
            //将doc转json
            String json = objectMapper.writeValueAsString(productDoc);
            indexRequest.source(json,XContentType.JSON);
            bulkRequest.add(indexRequest);
        }
        restHighLevelClient.bulk(bulkRequest,RequestOptions.DEFAULT);
        log.info("ApplicationRunListener.run业务结束，完成数据更新!");
    }
}
