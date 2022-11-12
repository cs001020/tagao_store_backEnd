package com.chen.vo;

import com.chen.pojo.Order;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 查询订单需要返回结果
 *
 * @author CHEN
 * @date 2022/11/13
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class OrderVo extends Order {

    @JsonProperty("product_name")
    private String productName;
    @JsonProperty("product_picture")
    private String productPicture;

}

