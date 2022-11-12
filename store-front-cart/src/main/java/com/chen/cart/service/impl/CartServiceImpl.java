package com.chen.cart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.cart.mapper.CartMapper;
import com.chen.cart.service.CartService;
import com.chen.client.ProductClient;
import com.chen.param.CartListParam;
import com.chen.param.CartParam;
import com.chen.param.ProductCollectParam;
import com.chen.param.ProductIdParam;
import com.chen.pojo.Cart;
import com.chen.pojo.Product;
import com.chen.untils.R;
import com.chen.vo.CartVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 车服务impl
 *
 * @author CHEN
 * @date 2022/11/12
 */
@Service
@Slf4j
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {
    @Resource
    private ProductClient productClient;

    @Override
    public R saveCart(CartParam cartParam) {
        //查询商品数据
        ProductIdParam productIdParam = new ProductIdParam();
        productIdParam.setProductID(cartParam.getProductId());
        Product product = productClient.productDetail(productIdParam);
        if (Objects.isNull(product)){
            return R.fail("商品不存在添加失败");
        }
        //检查库存
        if (product.getProductNum()==0){
            R ok = R.ok("库存不足添加失败");
            ok.setCode("003");
            return ok;
        }
        //检查是否添加
        Cart cart = lambdaQuery().eq(Cart::getUserId, cartParam.getUserId())
                .eq(Cart::getProductId, cartParam.getProductId()).one();
        if (!Objects.isNull(cart)){
            //购物车存在
            //数量+1
            cart.setNum(cart.getNum()+1);
            //插入数据
            updateById(cart);
            //返回002 提示成功
            R ok = R.ok("购物车存在该商品，数量+1");
            ok.setCode("002");
            log.info("CartServiceImpl.saveCart业务结束，结果:{}",ok);
            return ok;
        }
        //添加购物车
        Cart newCart = new Cart();
        newCart.setNum(1);
        newCart.setProductId(cartParam.getProductId());
        newCart.setUserId(cartParam.getUserId());
        boolean save = save(newCart);
        //结果封装返回
        log.info("CartServiceImpl.saveCart业务结束，结果:{}",save);
        CartVo cartVo = new CartVo(product, newCart);
        return R.ok("添加成功",cartVo);
    }

    @Override
    public R cartList(CartListParam cartListParam) {
        //根据用id查询购物车信息
        List<Cart> cartList = lambdaQuery()
                .eq(Cart::getUserId, cartListParam.getUserId())
                .list();
        //判断是否存在
        if (cartList.size() == 0){
            return R.ok("购物车空空如也", Collections.emptyList());
        }
        //存在 进行远程调用 获取商品信息
        ProductCollectParam productCollectParam = new ProductCollectParam();
        productCollectParam.setProductIds(cartList.stream().map(Cart::getProductId).collect(Collectors.toList()));
        List<Product> products = productClient.cartList(productCollectParam);
        //封装成vo 返回
        Map<Integer, Product> productMap = products.stream().collect(Collectors.toMap(Product::getProductId, product -> product));
        List<CartVo> cartVoList=new ArrayList<>(cartList.size());
        cartList.forEach(cart -> {
            Product product = productMap.get(cart.getProductId());
            cartVoList.add(new CartVo(product,cart));
        });
        log.info("CartServiceImpl.cartList业务结束，结果:{}",cartVoList);
        return R.ok("查询成功",cartVoList);
    }

    @Override
    public R updateCart(Cart cart) {
        //查询商品数据
        ProductIdParam productIdParam = new ProductIdParam();
        productIdParam.setProductID(cart.getProductId());
        Product product = productClient.productDetail(productIdParam);
        //判断库存
        if (Objects.isNull(product)||product.getProductNum()<cart.getNum()){
            log.info("CartServiceImpl.updateCart业务结束，结果:{}","更新购物车失败，库存不足！");
            return R.fail("库存不住");
        }
        //修改数据
        boolean update = lambdaUpdate().eq(Cart::getUserId, cart.getUserId())
                .eq(Cart::getProductId, cart.getProductId())
                .set(Cart::getNum, cart.getNum()).update();
        log.info("CartServiceImpl.updateCart业务结束，结果:{}",update);
        return R.ok("修改购物车数量成功");
    }

    @Override
    public R removeCart(Cart cart) {
        LambdaQueryWrapper<Cart> cartLambdaQueryWrapper = new LambdaQueryWrapper<>();
        cartLambdaQueryWrapper.eq(Cart::getUserId, cart.getUserId())
                .eq(Cart::getProductId, cart.getProductId());
        boolean remove = remove(cartLambdaQueryWrapper);
        log.info("CartServiceImpl.removeCart业务结束，结果:{}",remove);
        return R.ok("购物车删除成功");
    }
}
