package com.chen.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.client.CategoryClient;
import com.chen.client.SearchClient;
import com.chen.param.*;
import com.chen.pojo.Category;
import com.chen.pojo.Picture;
import com.chen.pojo.Product;
import com.chen.product.mapper.ProductMapper;
import com.chen.product.service.PictureService;
import com.chen.product.service.ProductService;
import com.chen.to.OrderToProduct;
import com.chen.untils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 产品服务impl
 *
 * @author CHEN
 * @date 2022/11/11
 */
@Service
@Slf4j
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
    /**引入feign客户端需要在启动类添加配置注解*/
    @Resource
    private CategoryClient categoryClient;
    @Resource
    private SearchClient searchClient;
    @Resource
    private PictureService pictureService;

    @Override
    @Cacheable(value = "list.product",key = "#productPromoParam.categoryName",cacheManager = "cacheManagerDay")
    public R promo(ProductPromoParam productPromoParam) {
        //远程调用 获得类别数据
        R categoryByName = categoryClient.getCategoryByName(productPromoParam.getCategoryName());
        //获取失败
        if (R.FAIL_CODE.equals(categoryByName.getCode())){
            log.info("ProductServiceImpl.promo业务结束，结果:{}","类别查询失败");
            return categoryByName;
        }
        //获取成功
        LinkedHashMap<String,Object> category= (LinkedHashMap<String, Object>) categoryByName.getData();
        Integer categoryId = (Integer) category.get("category_id");
        //查询数据库 销售量降序 7条
        Page<Product> page = lambdaQuery()
                .eq(Product::getCategoryId, categoryId)
                .orderByDesc(Product::getProductSales)
                .page(new Page<>(1, 7));
        //获取数据
        List<Product> productList = page.getRecords();
        log.info("ProductServiceImpl.promo业务结束，结果:{}",productList);
        return R.ok("查询成功",productList);
    }

    @Override
    @Cacheable(value = "list.product",key = "#productHotParam.categoryName")
    public R hots(ProductHotParam productHotParam) {
        //远程调用类别服务
        R hots = categoryClient.hots(productHotParam);
        if (R.FAIL_CODE.equals(hots.getCode())){
            log.info("ProductServiceImpl.hots业务结束，结果:{}",hots.getMsg());
            return hots;
        }
        //获得结果
        List<Integer> categoryIdList = (List<Integer>) hots.getData();
        //数据库查询
        Page<Product> page = lambdaQuery()
                .in(Product::getCategoryId, categoryIdList)
                .orderByDesc(Product::getProductSales)
                .page(new Page<>(1, 7));
        //结果封装
        List<Product> productList = page.getRecords();
        R ok = R.ok("查询成功", productList);
        log.info("ProductServiceImpl.hots业务结束，结果:{}",ok);
        return ok;
    }

    @Override
    @Cacheable(value = "list.category",key = "#root.methodName")
    public R categoryList() {
        R r = categoryClient.list();
        log.info("ProductServiceImpl.categoryList业务结束，结果:{}",r);
        return r;
    }

    @Override
    @Cacheable(value = "list.product",key = "#params.categoryID+'-'+#params.currentPage+'-'+#params.pageSize")
    public R byCategoryIds(ProductCategoryIdsParams params) {
        List<Integer> categoryID = params.getCategoryID();
        LambdaQueryChainWrapper<Product> productLambdaQueryChainWrapper = lambdaQuery();
        //判断时候传入类型id集合
        if (!categoryID.isEmpty()){
            productLambdaQueryChainWrapper.in(Product::getCategoryId,categoryID);
        }
        Page<Product> page = productLambdaQueryChainWrapper.page(new Page<>(params.getCurrentPage(), params.getPageSize()));
        R ok = R.ok("查询成功", page.getRecords(), page.getTotal());
        log.info("ProductServiceImpl.byCategoryIds业务结束，结果:{}",ok);
        return ok;
    }

    @Override
    @Cacheable(value = "product",key = "#param.productID")
    public R detail(ProductIdParam param) {
        R ok = R.ok(getById(param.getProductID()));
        log.info("ProductServiceImpl.detail业务结束，结果:{}",ok);
        return ok;
    }

    @Override
    @Cacheable(value = "picture",key = "#param.productID")
    public R picture(ProductIdParam param) {
        //查询数据库
        List<Picture> pictureList = pictureService
                .lambdaQuery()
                .eq(Picture::getProductId, param.getProductID())
                .list();
        //返回结果
        R ok = R.ok(pictureList);
        log.info("ProductServiceImpl.picture业务结束，结果:{}",ok);
        return ok;
    }

    @Override
    public R search(ProductSearchParam productSearchParam) {
        R result = searchClient.searchProduct(productSearchParam);
        log.info("ProductServiceImpl.search业务结束，结果:{}",result);
        return result;
    }

    @Override
    @Cacheable(value = "list.product",key = "#productCollectParam.productIds")
    public R getProductListByProductIds(ProductCollectParam productCollectParam) {
        //查询数据库
        List<Product> productList = lambdaQuery().in(Product::getProductId, productCollectParam.getProductIds()).list();

        //封装结果
        R ok = R.ok("查询成功", productList);
        log.info("ProductServiceImpl.getProductListByProductIds业务结束，结果:{}",ok);
        return ok;
    }

    @Override
    public void subNumber(List<OrderToProduct> orderToProducts) {
        //转换为map
        Map<Integer, Integer> collect = orderToProducts.stream().collect(Collectors.toMap(OrderToProduct::getProductId, OrderToProduct::getNum));
        //生成更新数据
        Set<Integer> integers = collect.keySet();
        List<Product> productList = baseMapper.selectBatchIds(integers);
        for (Product product : productList) {
            Integer num = collect.get(product.getProductId());
            product.setProductNum(product.getProductNum()-num);
            product.setProductSales(product.getProductSales()+num);
        }
        //批量更新
        updateBatchById(productList);
        log.info("ProductServiceImpl.subNumber业务结束，结果:{}","库存和销售量的修改完毕");
    }
}
