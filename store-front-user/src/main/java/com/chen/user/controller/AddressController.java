package com.chen.user.controller;

import com.chen.param.AddressListParam;
import com.chen.param.AddressRemoveParam;
import com.chen.pojo.Address;
import com.chen.untils.R;
import com.chen.user.service.AddressService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 解决控制器
 *
 * @author 10065
 * @date 2022/11/10
 */
@RestController
@RequestMapping("/user/address")
public class AddressController {
    @Resource
    private AddressService addressService;

    @PostMapping("/list")
    public R addressList(@RequestBody @Validated AddressListParam addressListParam,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return R.fail("参数异常,查询失败");
        }
        return addressService.list(addressListParam);
    }
    @PostMapping("/save")
    public R saveAddress(@RequestBody @Validated Address address,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return R.fail("参数异常,添加失败");
        }
        return addressService.saveAddress(address);
    }
    @PostMapping("/remove")
    public R removeAddress(@RequestBody @Validated AddressRemoveParam addressRemoveParam,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return R.fail("参数异常,删除失败");
        }
        return addressService.removeAddress(addressRemoveParam);
    }
}
