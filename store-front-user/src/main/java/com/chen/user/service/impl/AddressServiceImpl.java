package com.chen.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.param.AddressListParam;
import com.chen.param.AddressRemoveParam;
import com.chen.pojo.Address;
import com.chen.untils.R;
import com.chen.user.Mapper.AddressMapper;
import com.chen.user.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 地址服务实现类
 *
 * @author 10065
 * @date 2022/11/10
 */
@Service
@Slf4j
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {

    @Override
    public R list(AddressListParam addressListParam) {
        //数据库查询
        List<Address> list = lambdaQuery().eq(Address::getUserId, addressListParam.getUserId()).list();
        //结果封装
        log.info("AddressServiceImpl.list业务结束，结果:{}", R.ok("查询成功",list));
        return R.ok("查询成功",list);
    }

    @Override
    public R saveAddress(Address address) {
        //插入数据
        int rows = baseMapper.insert(address);
        if (rows==0){
            log.info("AddressServiceImpl.saveAddress业务结束，结果:{}","新增加地址失败");
            return R.fail("插入异常，请稍后再试！");
        }
        List<Address> list = lambdaQuery().eq(Address::getUserId, address.getUserId()).list();
        return R.ok("查询成功",list);
    }

    @Override
    public R removeAddress(AddressRemoveParam addressRemoveParam) {
        boolean isSuccess = removeById(addressRemoveParam.getId());
        if (!isSuccess){
            log.info("AddressServiceImpl.removeAddress业务结束，结果:{}","地址删除失败");
            return R.fail("删除异常，请稍后再试！");
        }
        log.info("AddressServiceImpl.removeAddress业务结束，结果:{}","地址删除成功");
        return R.ok("地址删除成功");
    }
}
