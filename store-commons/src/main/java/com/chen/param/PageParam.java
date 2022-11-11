package com.chen.param;

import lombok.Data;

/**
 * 页面参数
 *
 * @author CHEN
 * @date 2022/11/11
 */
@Data
public class PageParam {
    private Integer currentPage=1;
    private Integer pageSize=15;
}
