package com.chen.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.param.AddressListParam;
import com.chen.param.AddressRemoveParam;
import com.chen.pojo.Address;
import com.chen.untils.R;

/**
 * 地址服务
 *
 * @author 10065
 * @date 2022/11/10
 */
public interface AddressService extends IService<Address> {

    /**
     * 根据用户id查询地址列表
     *
     * @param addressListParam 地址列表参数
     * @return {@link R}
     */
    R list(AddressListParam addressListParam);

    /**
     * 添加地址
     *
     * @param address 地址
     * @return {@link R}
     */
    R saveAddress(Address address);

    /**
     * 删除地址
     *
     * @param addressRemoveParam 地址删除参数
     * @return {@link R}
     */
    R removeAddress(AddressRemoveParam addressRemoveParam);
}
