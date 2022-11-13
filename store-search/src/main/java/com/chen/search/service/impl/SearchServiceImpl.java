package com.chen.search.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.chen.doc.ProductDoc;
import com.chen.param.ProductSearchParam;
import com.chen.pojo.Product;
import com.chen.search.service.SearchService;
import com.chen.untils.R;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.naming.directory.SearchResult;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 搜索服务impl
 *
 * @author CHEN
 * @date 2022/11/11
 */
@Service
@Slf4j
public class SearchServiceImpl implements SearchService {

    @Resource
    private RestHighLevelClient restHighLevelClient;
    @Override
    public R searchProduct(ProductSearchParam productSearchParam) {
        SearchRequest searchRequest = new SearchRequest("product");
        String search = productSearchParam.getSearch();
        //判断关键字是否为空
        if (StringUtils.isEmpty(search)){
            searchRequest.source().query(QueryBuilders.matchAllQuery());
        }else {
            searchRequest.source().query(QueryBuilders.matchQuery("all",search));
        }
        //添加分页属性
        Integer currentPage = productSearchParam.getCurrentPage();
        Integer pageSize = productSearchParam.getPageSize();
        searchRequest.source().from((currentPage-1)*pageSize);
        searchRequest.source().size(pageSize);
        //es查询
        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.info("SearchServiceImpl.searchProduct业务结束，结果:{}","搜索失败");
            return R.fail("搜索失败");
        }
        //结果处理
        SearchHits hits = searchResponse.getHits();
        long total = hits.getTotalHits().value;
        //获取数据集合
        SearchHit[] hitsHits = hits.getHits();
        List<Product> products=new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        for (SearchHit hitsHit : hitsHits) {
            //Product的json数据
            String sourceAsString = hitsHit.getSourceAsString();
            try {
                Product  product = objectMapper.readValue(sourceAsString, Product.class);
                products.add(product);
            } catch (JsonProcessingException e) {
                log.info("SearchServiceImpl.searchProduct业务结束，结果:{}","搜索结果反序列化失败");
            }
        }
        //返回结果
        R ok = R.ok("搜索成功", products, total);
        log.info("SearchServiceImpl.searchProduct业务结束，结果:{}",ok);
        return ok;
    }

    @Override
    public R save(Product product) throws IOException {
        IndexRequest indexRequest = new IndexRequest("product").id(product.getProductId().toString());
        ProductDoc productDoc = new ProductDoc(product);
        ObjectMapper objectMapper = new ObjectMapper();
        indexRequest.source(objectMapper.writeValueAsString(productDoc), XContentType.JSON);
        restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        return R.ok("数据同步成功");
    }

    @Override
    public R remove(Integer productId) throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest("product").id(productId.toString());
        restHighLevelClient.delete(deleteRequest,RequestOptions.DEFAULT);
        return R.ok("数据同步成功");
    }
}
