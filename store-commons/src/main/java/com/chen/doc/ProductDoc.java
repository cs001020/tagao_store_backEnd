package com.chen.doc;

import com.chen.pojo.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 产品文档
 *
 * @author CHEN
 * @date 2022/11/11
 */
@Data
@NoArgsConstructor
public class ProductDoc extends Product {

    /**
     * 商品名称和商品标题和商品描述的综合值
     */
    private String all;

    public ProductDoc(Product product) {
        super(product.getProductId(), product.getProductName(), product.getCategoryId(),
                product.getProductTitle(), product.getProductIntro(), product.getProductPicture(),
                product.getProductPrice(), product.getProductSellingPrice(), product.getProductNum(),
                product.getProductSales());
        this.all = product.getProductName()+product.getProductTitle()+product.getProductIntro();
    }
}
